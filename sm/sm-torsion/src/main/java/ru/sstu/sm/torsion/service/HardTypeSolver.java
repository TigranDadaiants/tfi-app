package ru.sstu.sm.torsion.service;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.service.solver.AbstractHardTypeSolver;
import ru.sstu.sm.torsion.domain.AbstractSection;

/**
 * <code>HardTypeSolver</code> class is solver implementation for
 * Torsion Task.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class HardTypeSolver
		extends AbstractHardTypeSolver<AbstractSection> {

	/**
	 * Initializes solver.
	 *
	 * @param engine engine
	 */
	public HardTypeSolver(TaskEngine engine) {
		super(engine);
	}

	/**
	 * Provides value for equation.
	 *
	 * @param section section
	 * @return value
	 */
	protected double getEquationValue(AbstractSection section) {
		return section.getInertia() * section.getYoung().getSI();
	}

	/**
	 * Provides value for "free column" in system of equations.
	 *
	 * @return polynomial value
	 */
	protected PolynomialFunction getFreeColumnValue() {
		return PolynomialUtil.create();
	}
}
