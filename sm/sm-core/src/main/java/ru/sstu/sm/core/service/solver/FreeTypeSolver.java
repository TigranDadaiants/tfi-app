package ru.sstu.sm.core.service.solver;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.log4j.Logger;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;

/**
 * <code>FreeTypeSolver </code> class is solver for "Free Type Tasks", i.e.
 * statically solvable tasks.
 *
 * @author Denis A. Murashev
 * @param <T> type of section
 * @since SM 1.0
 */
public class FreeTypeSolver<T extends Section> extends AbstractSolver<T> {

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(FreeTypeSolver.class);

	/**
	 * Initializes abstract solver.
	 *
	 * @param engine engine
	 */
	public FreeTypeSolver(AbstractEngine<T> engine) {
		super(engine);
	}

	/**
	 * Performs calculations for task solution. Updates configuration sections.
	 */
	public final void calculate() {
		log.debug("Static solvable task is being solved");
		Config<T> config = getEngine().getConfig();
		for (int index = 0; index < config.size(); index++) {
			PolynomialFunction value = getEngine().getRestInfluence(index);
			log.debug("Index: " + index + "; Value = " + value);
			Section section = (index > 0) ? config.get(index - 1) : null;
			config.get(index).update(PolynomialUtil.create(value), section);
		}
	}
}
