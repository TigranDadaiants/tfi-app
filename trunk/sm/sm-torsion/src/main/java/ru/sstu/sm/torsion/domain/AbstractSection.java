package ru.sstu.sm.torsion.domain;

import javax.measure.quantity.Length;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>AbstractSection</code> class is base section class for all types of
 * sections for Torsion Task.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractSection extends Section {

	/**
	 * Moment.
	 */
	public static final int TORQUE = 0;

	/**
	 * Angle.
	 */
	public static final int ANGLE = 1;

	/**
	 * Total angle.
	 */
	public static final int TOTAL = 2;

	/**
	 * Inertia.
	 */
	public static final int INERTIA = 3;

	/**
	 * Minimal size.
	 */
	public static final int MINIMAL_SIZE = 4;

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -865690004540133132L;

	/**
	 * External torque.
	 */
	@XmlElement
	private TorqueValue torque;

	/**
	 * Just for JAXB.
	 */
	protected AbstractSection() {
	}

	/**
	 * Initializes section.
	 *
	 * @param length length
	 * @param young  Young module
	 * @param torque torque
	 */
	protected AbstractSection(LengthValue length, PressureValue young,
			TorqueValue torque) {
		setLength(length);
		setYoung(young);
		this.torque = torque;
		put(TORQUE, PolynomialUtil.create());
		put(ANGLE, PolynomialUtil.create());
		put(TOTAL, PolynomialUtil.create());
		put(INERTIA, PolynomialUtil.create());
		put(MINIMAL_SIZE, PolynomialUtil.create());
	}

	/**
	 * @return the torque
	 */
	public TorqueValue getTorque() {
		return torque;
	}

	/**
	 * @param torque the torque to set
	 */
	public void setTorque(TorqueValue torque) {
		this.torque = torque;
	}

	/**
	 * Updates default expression with given polynomial function.
	 *
	 * @param polynom  polynomial function
	 * @param previous previous section
	 */
	@Override
	public void update(PolynomialFunction polynom, Section previous) {
		put(TORQUE, polynom);
		double factor = getYoung().getSI() * getInertia();
		PolynomialFunction p = PolynomialUtil.divide(polynom, factor);
		PolynomialFunction angle = PolynomialUtil.integrate(p, 0.0);
		put(ANGLE, angle);
		double position = previous != null
				? previous.getLength().getSI()
				: 0.0;
		double e = previous != null
				? previous.getValue(TOTAL, position)
				: 0.0;
		p = PolynomialUtil.divide(polynom, factor);
		PolynomialFunction total = PolynomialUtil.integrate(p, e);
		put(TOTAL, total);
		put(INERTIA, PolynomialUtil.create(getInertia()));
		put(MINIMAL_SIZE, PolynomialUtil.create(getMinimalSize(1.0)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		final String colon = ": ";
		final String semicolon = "; ";
		StringBuilder buffer = new StringBuilder();
		buffer.append(TextUtil.get("title.length")).append(colon)
				.append(toString(getLength(), Length.UNIT)).append(semicolon);
		buffer.append(TextUtil.get("title.young")).append(colon)
				.append(toString(getYoung(), PressureValue.UNIT))
				.append(semicolon);
		buffer.append(toString(torque, TorqueValue.UNIT)).append(semicolon);
		return buffer.toString();
	}

	/**
	 * Provides inertia.
	 *
	 * @return the moment of inertia for the section
	 */
	public abstract double getInertia();

	/**
	 * Provides minimal possible size for section.
	 *
	 * @param limitValue limit value for tension
	 * @return limit size for given limit tension value
	 */
	protected abstract double getMinimalSize(double limitValue);
}
