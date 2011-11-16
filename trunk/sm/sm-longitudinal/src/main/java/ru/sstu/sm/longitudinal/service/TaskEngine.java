package ru.sstu.sm.longitudinal.service;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.service.ConfigManager;
import ru.sstu.sm.core.service.solver.AbstractHardTypeSolver;
import ru.sstu.sm.longitudinal.domain.SimpleSection;
import ru.sstu.sm.longitudinal.domain.TaskConfig;

/**
 * <code>TaskEngine</code> class is concrete engine for Longitudinal task.
 *
 * @author Denis Murashev
 * @since SM 1.0
 */
public final class TaskEngine extends AbstractEngine<SimpleSection> {

	/**
	 * Initializes engine.
	 */
	public TaskEngine() {
		super(new TaskConfig());
		setConfigManager(new ConfigManager<SimpleSection>(TaskConfig.class));
	}

	/**
	 * Provides statically unsolvable solver if necessary.
	 *
	 * @return statically unsolvable solver
	 */
	@Override
	protected AbstractHardTypeSolver<SimpleSection> getHardTypeSolver() {
		return new HardTypeSolver(this);
	}

	/**
	 * Provides sum of influences at given section.
	 *
	 * @param index index of section
	 * @return sum of influences
	 */
	public PolynomialFunction getRestInfluence(int index) {
		double value = 0.0;
		int size = getConfig().size();
		for (int i = index; i < size; i++) {
			ForceValue load = (getConfig().get(i)).getLoad();
			value += load.getSI();
		}
		return PolynomialUtil.create(value);
	}

	/**
	 * Updates all observers.
	 */
	protected void updateAll() {
		setChanged();
		notifyObservers(this);
	}
}
