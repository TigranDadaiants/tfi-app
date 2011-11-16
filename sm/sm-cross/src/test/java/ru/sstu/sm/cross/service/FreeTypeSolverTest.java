package ru.sstu.sm.cross.service;

import static ru.sstu.math.util.PolynomialUtil.create;
import static ru.sstu.sm.cross.service.ConfigHelper.config;
import static ru.sstu.sm.cross.service.ConfigHelper.map;
import static ru.sstu.sm.cross.service.ConfigHelper.section;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.solver.AbstractSolver;
import ru.sstu.sm.core.service.solver.FreeTypeSolver;
import ru.sstu.sm.cross.domain.SimpleSection;
import ru.sstu.sm.cross.domain.TaskConfig;

/**
 * Class <code>StaticSolvableSolverTest</code> is unit test for
 * {@link FreeTypeSolver}.
 *
 * @author Denis A. Murashev
 * @since 2.0
 */
@SuppressWarnings("unchecked")
public class FreeTypeSolverTest extends TestCase {

	/**
	 * Configuration.
	 */
	private static final TaskConfig CONFIG = config(
			TaskConfig.Type.FREE,
			section(3.1, 2e5, 1.0, 0.0, 0.0, -30.0, false),
			section(1.8, 2e5, 1.0, 0.0, 23.0, 0.0, true),
			section(2.4, 2e5, 1.0, -56.0, 0.0, 0.0, false)
	);

	/**
	 * Expected result.
	 */
	private static final List<Map<Integer, PolynomialFunction>> EXPECTED
		= Arrays.asList(
			map(create(149000, -30000), create(-529950, 149000, -15000)),
			map(create(56000), create(-212200, 56000)),
			map(create(56000), create(-134400, 56000))
		);

	/**
	 * @throws Exception if some error occurs
	 */
	public void testSolver() throws Exception {
		TaskEngine engine = new TaskEngine();
		engine.setConfig(CONFIG);
		AbstractSolver<?> solver = new FreeTypeSolver(engine);
		solver.calculate();
		for (int i = 0; i < engine.getConfig().size(); i++) {
			Section section = engine.getConfig().get(i);
			assertEquals("Load", EXPECTED.get(i).get(SimpleSection.LOAD),
					section.get(SimpleSection.LOAD));
			assertEquals("Moment", EXPECTED.get(i).get(SimpleSection.TORQUE),
					section.get(SimpleSection.TORQUE));
		}
	}
}
