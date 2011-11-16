package ru.sstu.sm.longitudinal.service;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.service.solver.AbstractHardTypeSolver;
import ru.sstu.sm.longitudinal.domain.SimpleSection;
import ru.sstu.sm.longitudinal.domain.TaskConfig;

/**
 * <code>HardTypeSolver</code> class is abstract solver implementation.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class HardTypeSolver
		extends AbstractHardTypeSolver<SimpleSection> {

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
	protected double getEquationValue(SimpleSection section) {
		return section.getCrossSection().getSI() * section.getYoung().getSI();
	}

	/**
	 * Provides value for "free column" in system of equations.
	 *
	 * @return polynomial value
	 */
	protected PolynomialFunction getFreeColumnValue() {
		return PolynomialUtil.create(((TaskConfig) getEngine().getConfig())
				.getDelta().getSI());
	}
}
