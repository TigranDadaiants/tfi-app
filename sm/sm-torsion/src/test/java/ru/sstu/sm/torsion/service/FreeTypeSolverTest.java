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
import ru.sstu.sm.core.service.solver.FreeTypeSolver;
import ru.sstu.sm.torsion.domain.AbstractSection;
import ru.sstu.sm.torsion.domain.TaskConfig;

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
	 * Configuration.
	 */
	private static final TaskConfig CONFIG = config(
			TaskConfig.Type.FREE,
			10.0,
			circularSection(100.0, 0.5, 8e4, 25),
			annularSection(100.0, 50.0, 0.5, 8e4, -30),
			rectangularSection(100.0, 100.0, 1.0, 8e4, 0)
	);

	/**
	 * Expected value.
	 */
	private static final List<Map<Integer, PolynomialFunction>> EXPECTED
		= Arrays.asList(
			map(create(-5000), create(0, -0.006366197723675812)),
			map(create(-3e4), create(-0.003183098861837906,
					-0.04074366543152519)),
			map(create(0), create(-0.0235549315776005))
		);

	/**
	 * Tests solver.
	 *
	 * @throws Exception if some error occurs
	 */
	public void testSolver() throws Exception {
		TaskEngine engine = new TaskEngine();
		engine.setConfig(CONFIG);
		AbstractSolver solver = new FreeTypeSolver(engine);
		solver.calculate();
		for (int i = 0; i < engine.getConfig().size(); i++) {
			Section section = engine.getConfig().get(i);
			assertEquals("Moment", section.get(AbstractSection.TORQUE),
					EXPECTED.get(i).get(AbstractSection.TORQUE));
			assertEquals("Total Angle", section.get(AbstractSection.TOTAL),
					EXPECTED.get(i).get(AbstractSection.TOTAL));
		}
	}
}
