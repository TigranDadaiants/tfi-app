package ru.sstu.sm.longitudinal.service;

import static java.util.Arrays.asList;
import static ru.sstu.math.util.PolynomialUtil.create;
import static ru.sstu.sm.longitudinal.service.ConfigHelper.config;
import static ru.sstu.sm.longitudinal.service.ConfigHelper.map;
import static ru.sstu.sm.longitudinal.service.ConfigHelper.section;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.sm.core.service.solver.AbstractSolver;
import ru.sstu.sm.core.service.solver.FreeTypeSolver;
import ru.sstu.sm.longitudinal.domain.SimpleSection;
import ru.sstu.sm.longitudinal.domain.TaskConfig;

/**
 * <code>FreeTypeSolverTest</code> class is unit test for
 * {@link FreeTypeSolver}.
 *
 * @author Denis A. Murashev
 * @since 2.0
 */
@SuppressWarnings("unchecked")
public class FreeTypeSolverTest extends TestCase {

	/**
	 * Default accuracy.
	 */
	private static final double DELTA = 1e-7;

	/**
	 * Test configuration.
	 */
	private static final TaskConfig CONFIG = config(
		TaskConfig.Type.FREE,
		0.0,
		section(5.0, 0.5, 2e5, -1),
		section(10.0, 0.5, 2e5, 2),
		section(10.0, 1.0, 2e5, 0)
	);

	/**
	 * Expected result.
	 */
	private static final List<Map<Integer, PolynomialFunction>> EXPECTED
		= asList(
			map(create(1e3), create(2e6), create(0, 1E-5)),
			map(create(2e3), create(2e6), create(0, 1E-5)),
			map(create(0), create(0), create(0))
		);

	/**
	 * Tests solver.
	 *
	 * @throws Exception if some error occurs
	 */
	public void testSolver() throws Exception {
		TaskEngine engine = new TaskEngine();
		engine.setConfig(CONFIG);
		AbstractSolver<?> solver = new FreeTypeSolver(engine);
		solver.calculate();
		for (int i = 0; i < engine.getConfig().size(); i++) {
			SimpleSection section = engine.getConfig().get(i);
			assertEquals("Load", EXPECTED.get(i).get(SimpleSection.LOAD),
					section.get(SimpleSection.LOAD), DELTA);
			assertEquals("Tension", EXPECTED.get(i).get(SimpleSection.TENSION),
					section.get(SimpleSection.TENSION), DELTA);
			assertEquals("Extension",
					EXPECTED.get(i).get(SimpleSection.EXTENSION),
					section.get(SimpleSection.EXTENSION), DELTA);
		}
	}

	/**
	 * Checks if two polynomials are equal.
	 *
	 * @param message  message
	 * @param expected expected polynomial
	 * @param actual   actual polynomial
	 * @param delta    delta
	 */
	private static void assertEquals(String message,
			PolynomialFunction expected, PolynomialFunction actual,
			double delta) {
		assertEquals(message, expected.degree(), actual.degree());
		for (int i = 0; i < actual.degree() + 1; i++) {
			assertEquals(message, expected.getCoefficients()[i],
					actual.getCoefficients()[i], delta);
		}
	}
}
