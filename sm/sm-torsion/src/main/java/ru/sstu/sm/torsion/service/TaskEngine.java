package ru.sstu.sm.torsion.service;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.service.ConfigManager;
import ru.sstu.sm.core.service.solver.AbstractHardTypeSolver;
import ru.sstu.sm.torsion.domain.AbstractSection;
import ru.sstu.sm.torsion.domain.TaskConfig;

/**
 * <code>TaskEngine</code> class implements the Strength of Material engine.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class TaskEngine extends AbstractEngine<AbstractSection> {

	/**
	 * Initializes task engine.
	 */
	public TaskEngine() {
		super(new TaskConfig());
		setConfigManager(new ConfigManager<AbstractSection>(TaskConfig.class));
	}

	/**
	 * Provides sum of influences at given section.
	 *
	 * @param index index of section
	 * @return sum of influences
	 */
	public PolynomialFunction getRestInfluence(int index) {
		double value = 0.0;
		for (int i = index; i < getConfig().size(); i++) {
			TorqueValue torque = getConfig().get(i).getTorque();
			value += torque.getSI();
		}
		return PolynomialUtil.create(value);
	}

	/**
	 * Provides statically unsolvable solver if necessary.
	 *
	 * @return statically unsolvable solver
	 */
	@Override
	protected AbstractHardTypeSolver<AbstractSection> getHardTypeSolver() {
		return new HardTypeSolver(this);
	}

	/**
	 * Updates all observers.
	 */
	protected void updateAll() {
		setChanged();
		notifyObservers(this);
	}
}
