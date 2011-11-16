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
 * <code>AnnularSection</code> class represents annular section.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class AnnularSection extends AbstractSection {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -4272964392605295632L;

	/**
	 * External diameter.
	 */
	@XmlElement
	private LengthValue externalDiameter;

	/**
	 * Internal diameter.
	 */
	@XmlElement
	private LengthValue internalDiameter;

	/**
	 * Just for JAXB.
	 */
	public AnnularSection() {
	}

	/**
	 * Initializes section.
	 *
	 * @param externalDiameter external diameter
	 * @param internalDiameter internal diameter
	 * @param length           length
	 * @param young            Young module
	 * @param torque           torque
	 */
	public AnnularSection(LengthValue externalDiameter,
			LengthValue internalDiameter, LengthValue length,
			PressureValue young, TorqueValue torque) {
		super(length, young, torque);
		this.externalDiameter = externalDiameter;
		this.internalDiameter = internalDiameter;
	}

	/**
	 * Provides external diameter.
	 *
	 * @return external diameter
	 */
	public LengthValue getExternalDiameter() {
		return externalDiameter;
	}

	/**
	 * Sets external diameter.
	 *
	 * @param externalDiameter external diameter
	 */
	public void setExternalDiameter(LengthValue externalDiameter) {
		this.externalDiameter = externalDiameter;
	}

	/**
	 * Provides internal diameter.
	 *
	 * @return internal diameter
	 */
	public LengthValue getInternalDiameter() {
		return internalDiameter;
	}

	/**
	 * Sets internal diameter.
	 *
	 * @param internalDiameter internal diameter
	 */
	public void setInternalDiameter(LengthValue internalDiameter) {
		this.internalDiameter = internalDiameter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getWidth() {
		return externalDiameter.getSI();
	}

	/**
	 * Provides inertia.
	 *
	 * @return the moment of inertia for the section
	 */
	public double getInertia() {
		double dEx = externalDiameter.getSI();
		double dIn = internalDiameter.getSI();
		final int c = 32;
		return Math.PI * (dEx * dEx * dEx * dEx - dIn * dIn * dIn * dIn) / c;
	}

	/**
	 * Provides minimal possible size for section.
	 *
	 * @param limitValue limit value for tension
	 * @return limit size for given limit tension value
	 */
	public double getMinimalSize(double limitValue) {
		double moment = getValue(TORQUE, 0.0);
		double dEx = externalDiameter.getSI();
		double dIn = internalDiameter.getSI();
		double r = dIn / dEx;
		final int c = 16;
		double d3 = c * Math.abs(moment) / Math.PI / limitValue
				/ (1 - r * r * r * r);
		return r * Math.cbrt(d3);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		final String colon = ": ";
		final String semicolon = "; ";
		StringBuilder buffer = new StringBuilder();
		buffer.append(TextUtil.get("type.annular")).append(". ");
		buffer.append(TextUtil.get("title.diameter.external")).append(colon)
				.append(toString(externalDiameter, SI.MILLIMETER))
				.append(semicolon);
		buffer.append(TextUtil.get("title.diameter.internal")).append(colon)
				.append(toString(internalDiameter, SI.MILLIMETER))
				.append(semicolon);
		buffer.append(super.toString());
		return buffer.toString();
	}
}
