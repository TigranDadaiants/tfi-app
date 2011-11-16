package ru.sstu.sm.cross.service;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.log4j.Logger;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.service.ConfigManager;
import ru.sstu.sm.core.service.solver.AbstractHardTypeSolver;
import ru.sstu.sm.core.service.solver.AbstractSolver;
import ru.sstu.sm.cross.domain.SimpleSection;
import ru.sstu.sm.cross.domain.TaskConfig;

/**
 * <code>TaskEngine</code> class is concrete engine for Cross Task.
 *
 * @author Denis Murashev
 * @since SM 1.0
 */
public final class TaskEngine extends AbstractEngine<SimpleSection> {

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(TaskEngine.class);

	/**
	 * Index of section with joint.
	 */
	private int jointIndex = -1;

	/**
	 * Initializes engine.
	 */
	public TaskEngine() {
		super(new TaskConfig());
		setConfigManager(new ConfigManager<SimpleSection>(TaskConfig.class));
	}

	/**
	 * Provides sum of influences at given section.
	 *
	 * @param index jointIndex of section
	 * @return sum of influences
	 */
	@Override
	public PolynomialFunction getRestInfluence(int index) {
		log.debug("Rest influence for section #" + index);
		PolynomialFunction value = PolynomialUtil.create();
		double arm = 0.0;
		int size = getConfig().size();
		for (int i = index; i < size; i++) {
			arm += getConfig().get(i).getLength().getSI();
			value = value.add(getMoment(i, arm));
		}
		log.debug("Index: " + index + "; Rest Influence:" + value);
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calculate() {
		double arm = updateJointSection();
		AbstractSolver<SimpleSection> solver = getSolver();
		if (solver != null) {
			solver.calculate();
		}
		if (arm != -1.0) {
			Section jointSection = getConfig().get(jointIndex);
			double a0 = -jointSection.getValue(SimpleSection.DEFLECTION,
					jointSection.getLength().getSI()) / arm;
			double v0 = 0.0;
			for (Section section : getConfig()) {
				PolynomialFunction angle = PolynomialUtil
						.sum(section.get(SimpleSection.ANGLE), a0);
				section.put(SimpleSection.ANGLE, angle);
				PolynomialFunction deflection = PolynomialUtil
						.integrate(angle, v0);
				section.put(SimpleSection.DEFLECTION, deflection);
				double position = section.getLength().getSI();
				v0 = section.getValue(SimpleSection.DEFLECTION, position);
			}
		}
		updateAll();
	}

	/**
	 * Provides statically unsolvable solver if necessary.
	 *
	 * @return statically unsolvable solver
	 */
	@Override
	protected AbstractHardTypeSolver<SimpleSection> getHardTypeSolver() {
		return null;
	}

	/**
	 * Updates all observers.
	 */
	@Override
	protected void updateAll() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * @param index joint section jointIndex
	 * @param arm   arm to joint
	 * @return moment
	 */
	private PolynomialFunction getMoment(int index, double arm) {
		PolynomialFunction moment = PolynomialUtil.create();
		SimpleSection section = getConfig().get(index);
		if (section.getLoad() != null) {
			double load = section.getLoad().getSI();
			moment = PolynomialUtil.sum(moment, load * arm, -load);
		}
		if (section.getTorque() != null) {
			moment = PolynomialUtil.sum(moment, section.getTorque().getSI());
		}
		if (section.getDistributedLoad() != null) {
			double length = section.getLength().getSI();
			double density = section.getDistributedLoad().getSI();
			if (arm > length) {
				moment = PolynomialUtil.sum(moment,
						density * length * (arm - length / 2.0),
						-density * length);
			} else {
				moment = PolynomialUtil.sum(moment,
						density * length * (arm - length / 2.0),
						-density * length,
						density / 2.0);
			}
		}
		log.debug("Moment for section #" + index + " is " + moment);
		return moment;
	}

	/**
	 * Updates section with joint.
	 *
	 * @return arm to joint or -1.0 if there is no joint
	 */
	private double updateJointSection() {
		jointIndex = findJoint();
		if (jointIndex >= getConfig().size()) {
			jointIndex = -1;
			return -1.0;
		}
		double arm = 0.0;
		for (int i = 0; i <= jointIndex; i++) {
			Section section = getConfig().get(i);
			arm += section.getLength().getSI();
		}
		SimpleSection section = getConfig().get(jointIndex);
		section.setLoad(new ForceValue(0.0));
		double load = -getRestInfluence(0).value(0.0) / arm;
		section.setLoad(new ForceValue(load));
		return arm;
	}

	/**
	 * @return index of joint section
	 */
	private int findJoint() {
		int index = 0;
		for (Section s : getConfig()) {
			SimpleSection section = (SimpleSection) s;
			if (section.isJoint()) {
				return index;
			}
			index++;
		}
		return index;
	}
}
