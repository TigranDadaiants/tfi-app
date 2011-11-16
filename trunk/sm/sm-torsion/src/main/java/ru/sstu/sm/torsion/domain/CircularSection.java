package ru.sstu.sm.torsion.domain;

import javax.measure.unit.SI;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>CircularSection</code> class represents circular section.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class CircularSection extends AbstractSection {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 6890859165594067916L;

	/**
	 * Diameter.
	 */
	@XmlElement
	private LengthValue diameter;

	/**
	 * Just for JAXB.
	 */
	public CircularSection() {
	}

	/**
	 * Initializes section.
	 *
	 * @param diameter diameter
	 * @param length   length
	 * @param young    Young module
	 * @param torque   torque
	 */
	public CircularSection(LengthValue diameter, LengthValue length,
			PressureValue young, TorqueValue torque) {
		super(length, young, torque);
		this.diameter = diameter;
	}

	/**
	 * Provides diameter.
	 *
	 * @return diameter
	 */
	public LengthValue getDiameter() {
		return diameter;
	}

	/**
	 * Sets diameter.
	 *
	 * @param diameter diameter
	 */
	public void setDiameter(LengthValue diameter) {
		this.diameter = diameter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getWidth() {
		return diameter.getSI();
	}

	/**
	 * Provides inertia.
	 *
	 * @return the moment of inertia for the section
	 */
	public double getInertia() {
		double d = diameter.getSI();
		final int c = 32;
		return Math.PI * d * d * d * d / c;
	}

	/**
	 * Provides minimal possible size for section.
	 *
	 * @param limitValue limit value for tension
	 * @return limit size for given limit tension value
	 */
	public double getMinimalSize(double limitValue) {
		double moment = getValue(TORQUE, 0.0);
		final int c = 16;
		double d3 = c * Math.abs(moment) / Math.PI / limitValue;
		return Math.cbrt(d3);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(TextUtil.get("type.circular")).append(". ");
		buffer.append(TextUtil.get("title.diameter")).append(": ")
				.append(toString(diameter, SI.MILLIMETER)).append("; ");
		buffer.append(super.toString());
		return buffer.toString();
	}
}
