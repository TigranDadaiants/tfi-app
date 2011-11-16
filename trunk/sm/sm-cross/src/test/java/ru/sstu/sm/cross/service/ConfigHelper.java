package ru.sstu.sm.cross.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.sm.core.domain.ForceDensityValue;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.InertiaMomentValue;
import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.cross.domain.SimpleSection;
import ru.sstu.sm.cross.domain.TaskConfig;

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
	 * @param length          length
	 * @param young           Young module
	 * @param crossSection    cross section inertia moment
	 * @param load            load
	 * @param moment          moment
	 * @param distributedLoad distributed load
	 * @param joint           joint
	 * @return section
	 */
	public static SimpleSection section(double length, double young,
			double crossSection, double load, double moment,
			double distributedLoad, boolean joint) {
		return new SimpleSection(
				new LengthValue(length),
				new PressureValue(young),
				new InertiaMomentValue(crossSection),
				new ForceValue(load),
				new TorqueValue(moment),
				new ForceDensityValue(distributedLoad),
				joint
		);
	}

	/**
	 * Creates {@link TaskConfig}.
	 *
	 * @param type     type
	 * @param sections sections
	 * @return <code>TaskConfig</code>
	 */
	public static TaskConfig config(TaskConfig.Type type,
			SimpleSection... sections) {
		TaskConfig config = new TaskConfig();
		config.setType(type);
		config.addAll(sections);
		return config;
	}

	/**
	 * @param load   load
	 * @param moment moment
	 * @return map
	 */
	public static Map<Integer, PolynomialFunction> map(
			PolynomialFunction load, PolynomialFunction moment) {
		Map<Integer, PolynomialFunction> map
				= new HashMap<Integer, PolynomialFunction>();
		map.put(SimpleSection.LOAD, load);
		map.put(SimpleSection.TORQUE, moment);
		return map;
	}
}
