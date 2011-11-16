package ru.sstu.sm.core.service.solver;

import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;

/**
 * <code>AbstractSolver</code> class represents abstract solver.
 *
 * @author Denis Murashev
 * @param <T> type of section
 * @since SM 1.0
 */
public abstract class AbstractSolver<T extends Section> {

	/**
	 * SM engine.
	 */
	private final AbstractEngine<T> engine;

	/**
	 * Initializes abstract solver.
	 *
	 * @param engine engine
	 */
	protected AbstractSolver(AbstractEngine<T> engine) {
		this.engine = engine;
	}

	/**
	 * Performs calculations for task solution. Updates configuration sections.
	 */
	public abstract void calculate();

	/**
	 * Provides task engine.
	 *
	 * @return the engine
	 */
	protected AbstractEngine<T> getEngine() {
		return engine;
	}
}
