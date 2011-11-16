package ru.sstu.sm.longitudinal.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.sm.core.domain.AreaValue;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.longitudinal.domain.SimpleSection;
import ru.sstu.sm.longitudinal.domain.TaskConfig;

/**
 * Class <code>ConfigHelper</code> helps to create test data configuration.
 *
 * @author Denis A. Murashev
 * @since SM 2.0
 */
public final class ConfigHelper {

	/**
	 * No instances needed.
	 */
	private ConfigHelper() {
	}

	/**
	 * Creates {@link SimpleSection}.
	 *
	 * @param crossSection cross section
	 * @param length       length
	 * @param young        Young module
	 * @param load         load
	 * @return <code>SimpleSection</code>
	 */
	public static SimpleSection section(double crossSection, double length,
			double young, double load) {
		return new SimpleSection(
				new AreaValue(crossSection),
				new LengthValue(length),
				new PressureValue(young),
				new ForceValue(load)
		);
	}

	/**
	 * Creates {@link TaskConfig}.
	 *
	 * @param type     type
	 * @param delta    delta
	 * @param sections sections
	 * @return <code>TaskConfig</code>
	 */
	public static TaskConfig config(TaskConfig.Type type, double delta,
			SimpleSection... sections) {
		TaskConfig config = new TaskConfig();
		config.setType(type);
		config.setDelta(new LengthValue(delta));
		config.addAll(sections);
		return config;
	}

	/**
	 * @param load      load
	 * @param tension   tension
	 * @param extension extension
	 * @return map
	 */
	public static Map<Integer, PolynomialFunction> map(PolynomialFunction load,
			PolynomialFunction tension, PolynomialFunction extension) {
		Map<Integer, PolynomialFunction> map
				= new HashMap<Integer, PolynomialFunction>();
		map.put(SimpleSection.LOAD, load);
		map.put(SimpleSection.TENSION, tension);
		map.put(SimpleSection.EXTENSION, extension);
		return map;
	}
}
