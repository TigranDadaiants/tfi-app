package ru.sstu.sm.torsion.service;

import java.util.HashMap;
import java.util.Map;

import javax.measure.unit.SI;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.torsion.domain.AbstractSection;
import ru.sstu.sm.torsion.domain.AnnularSection;
import ru.sstu.sm.torsion.domain.CircularSection;
import ru.sstu.sm.torsion.domain.RectangularSection;
import ru.sstu.sm.torsion.domain.TaskConfig;

/**
 * <code>ConfigHelper</code> class helps to create test data configuration.
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
	 * @param diameter diameter
	 * @param length   length
	 * @param young    Young module
	 * @param load     load
	 * @return section
	 */
	public static CircularSection circularSection(double diameter,
			double length, double young, double load) {
		return new CircularSection(
				new LengthValue(diameter, SI.MILLIMETER),
				new LengthValue(length),
				new PressureValue(young),
				new TorqueValue(load)
		);
	}

	/**
	 * @param externalDiameter external diameter
	 * @param internalDiameter internal diameter
	 * @param length           length
	 * @param young            Young module
	 * @param load             load
	 * @return section
	 */
	public static AnnularSection annularSection(double externalDiameter,
			double internalDiameter, double length, double young, double load) {
		return new AnnularSection(
				new LengthValue(externalDiameter, SI.MILLIMETER),
				new LengthValue(internalDiameter, SI.MILLIMETER),
				new LengthValue(length),
				new PressureValue(young),
				new TorqueValue(load)
		);
	}

	/**
	 * @param width  width
	 * @param height height
	 * @param length length
	 * @param young  Young module
	 * @param load   load
	 * @return section
	 */
	public static RectangularSection rectangularSection(double width,
			double height, double length, double young, double load) {
		return new RectangularSection(
				new LengthValue(width, SI.MILLIMETER),
				new LengthValue(height, SI.MILLIMETER),
				new LengthValue(length),
				new PressureValue(young),
				new TorqueValue(load)
		);
	}

	/**
	 * Creates {@link TaskConfig}.
	 *
	 * @param type     type
	 * @param limit    limit
	 * @param sections sections
	 * @return <code>TaskConfig</code>
	 */
	public static TaskConfig config(TaskConfig.Type type, double limit,
			AbstractSection... sections) {
		TaskConfig config = new TaskConfig();
		config.setType(type);
		config.setLimit(new PressureValue(limit));
		config.addAll(sections);
		return config;
	}

	/**
	 * @param moment moment
	 * @param angle  angle
	 * @return map
	 */
	public static Map<Integer, PolynomialFunction> map(
			PolynomialFunction moment, PolynomialFunction angle) {
		Map<Integer, PolynomialFunction> map
				= new HashMap<Integer, PolynomialFunction>();
		map.put(AbstractSection.TORQUE, moment);
		map.put(AbstractSection.TOTAL, angle);
		return map;
	}
}
