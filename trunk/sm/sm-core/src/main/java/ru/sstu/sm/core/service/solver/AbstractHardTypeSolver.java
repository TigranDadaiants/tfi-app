package ru.sstu.sm.core.service.solver;

import javax.measure.quantity.Length;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.DecompositionSolver;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.log4j.Logger;

import ru.sstu.math.util.MatrixUtil;
import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;

/**
 * <code>AbstractHardTypeSolver</code> class is abstract solver for "Hard Type
 * Tasks", i.e. statically unsolvable tasks.
 *
 * @author Denis A. Murashev
 * @param <T> type of section
 * @since SM 1.0
 */
public abstract class AbstractHardTypeSolver<T extends Section>
		extends AbstractSolver<T> {

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(AbstractHardTypeSolver.class);

	/**
	 * Initializes abstract solver.
	 *
	 * @param engine engine
	 */
	protected AbstractHardTypeSolver(AbstractEngine<T> engine) {
		super(engine);
	}

	/**
	 * Performs calculations for task solution. Updates configuration sections.
	 */
	@Override
	public final void calculate() {
		log.debug("Static unsolvable task is being solved");
		Config<T> config = getEngine().getConfig();
		int size = config.size() + 1;
		RealMatrix equations = new Array2DRowRealMatrix(size, size);
		PolynomialFunction[] column = new PolynomialFunction[size];

		for (int i = 0; i < size - 1; i++) {
			column[i] = getEngine().getRestInfluence(i);
			equations.setEntry(i, i, 1.0);
			equations.setEntry(i, size - 1, 1.0);
			T section = config.get(i);
			double value = section.getLength().doubleValue(Length.UNIT)
					/ getEquationValue(section);
			equations.setEntry(size - 1, i, value);
		}
		equations.setEntry(size - 1, size - 1, 0.0);
		column[size - 1] = getFreeColumnValue();

		DecompositionSolver solver = new LUDecompositionImpl(equations)
				.getSolver();
		RealMatrix inversed = solver.getInverse();
		PolynomialFunction[] result = MatrixUtil
				.multiply(inversed, column);
		for (int i = 0; i < size - 1; i++) {
			T section = (i > 0) ? config.get(i - 1) : null;
			config.get(i).update(result[i], section);
			log.debug("Index: " + i + "; Value = " + result[i]);
		}
	}

	/**
	 * Provides value for equation.
	 *
	 * @param section section
	 * @return value
	 */
	protected abstract double getEquationValue(T section);

	/**
	 * Provides value for "free column" in system of equations.
	 *
	 * @return polynomial value
	 */
	protected abstract PolynomialFunction getFreeColumnValue();
}
