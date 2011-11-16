package ru.sstu.sm.torsion.service;

import static ru.sstu.math.util.PolynomialUtil.create;
import static ru.sstu.sm.torsion.service.ConfigHelper.annularSection;
import static ru.sstu.sm.torsion.service.ConfigHelper.circularSection;
import static ru.sstu.sm.torsion.service.ConfigHelper.config;
import static ru.sstu.sm.torsion.service.ConfigHelper.map;
import static ru.sstu.sm.torsion.service.ConfigHelper.rectangularSection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.solver.AbstractSolver;
import ru.sstu.sm.torsion.domain.AbstractSection;
import ru.sstu.sm.torsion.domain.TaskConfig;

/**
 * <code>HardTypeSolverTest</code> class is unit test for
 * {@link HardTypeSolver}.
 *
 * @author Denis A. Murashev
 * @since 2.0
 */
@SuppressWarnings("unchecked")
public class HardTypeSolverTest extends TestCase {

	/**
	 * Configuration.
	 */
	private static final TaskConfig CONFIG = config(
			TaskConfig.Type.HARD,
			10.0,
			circularSection(100.0, 0.5, 8e4, 25),
			annularSection(100.0, 50.0, 0.6, 8e4, -30),
			rectangularSection(100.0, 100.0, 1.0, 8e4, 0)
	);

	/**
	 * Expected values.
	 */
	private static final List<Map<Integer, PolynomialFunction>> EXPECTED
		= Arrays.asList(
			map(create(6817.402286402482), create(0.0, 0.008680186183415551)),
			map(create(-18182.59771359752),
					create(0.004340093091707776, -0.024694189263961074)),
			map(create(11817.402286402481),
					create(-0.010476420466668867, 0.010476420466668863))
		);

	/**
	 * Tests solver.
	 *
	 * @throws Exception if some error occurs
	 */
	public void testSolver() throws Exception {
		TaskEngine engine = new TaskEngine();
		engine.setConfig(CONFIG);
		AbstractSolver<?> solver = new HardTypeSolver(engine);
		solver.calculate();
		for (int i = 0; i < engine.getConfig().size(); i++) {
			Section section = engine.getConfig().get(i);
			assertEquals("Moment", section.get(AbstractSection.TORQUE),
					EXPECTED.get(i).get(AbstractSection.TORQUE));
			assertEquals("Angle", section.get(AbstractSection.TOTAL),
					EXPECTED.get(i).get(AbstractSection.TOTAL));
		}
	}
}
