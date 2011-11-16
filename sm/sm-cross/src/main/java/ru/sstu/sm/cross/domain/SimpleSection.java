package ru.sstu.sm.cross.domain;

import javax.measure.quantity.Length;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.domain.ForceDensityValue;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.domain.InertiaMomentValue;
import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>SimpleSection</code> class is {@link Section} implementation for
 * Cross task.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class SimpleSection extends Section {

	/**
	 * Load.
	 */
	public static final int LOAD = 0;

	/**
	 * Torque.
	 */
	public static final int TORQUE = 1;

	/**
	 * Angle.
	 */
	public static final int ANGLE = 2;

	/**
	 * Deflection.
	 */
	public static final int DEFLECTION = 3;

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 4677526038062701826L;

	/**
	 * Cross section inertia moment.
	 */
	@XmlElement
	private InertiaMomentValue inertiaMoment;

	/**
	 * Load.
	 */
	@XmlElement(required = false)
	private ForceValue load;

	/**
	 * Torque load.
	 */
	@XmlElement(required = false)
	private TorqueValue torque;

	/**
	 * Distributed load.
	 */
	@XmlElement(required = false)
	private ForceDensityValue distributedLoad;

	/**
	 * Is there joint.
	 */
	@XmlAttribute
	private boolean joint;

	/**
	 * Just for JAXB.
	 */
	public SimpleSection() {
	}

	/**
	 * Initializes section.
	 *
	 * @param length          length
	 * @param young           Young module
	 * @param crossSection    cross section inertia moment
	 * @param load            load
	 * @param torque          torque
	 * @param distributedLoad distributed load
	 * @param joint           true if this is joint section
	 */
	public SimpleSection(LengthValue length, PressureValue young,
			InertiaMomentValue crossSection, ForceValue load,
			TorqueValue torque, ForceDensityValue distributedLoad,
			boolean joint) {
		setLength(length);
		setYoung(young);
		this.inertiaMoment = crossSection;
		this.load = load;
		this.torque = torque;
		this.distributedLoad = distributedLoad;
		this.joint = joint;
		put(LOAD, PolynomialUtil.create());
		put(TORQUE, PolynomialUtil.create());
		put(ANGLE, PolynomialUtil.create());
		put(DEFLECTION, PolynomialUtil.create());
	}

	/**
	 * Provides cross section.
	 *
	 * @return the crossSection
	 */
	public InertiaMomentValue getCrossSection() {
		return inertiaMoment;
	}

	/**
	 * Sets cross section.
	 *
	 * @param crossSection the crossSection to set
	 */
	public void setCrossSection(InertiaMomentValue crossSection) {
		this.inertiaMoment = crossSection;
	}

	/**
	 * Provides load.
	 *
	 * @return the load
	 */
	public ForceValue getLoad() {
		return load;
	}

	/**
	 * Sets load.
	 *
	 * @param load the load to set
	 */
	public void setLoad(ForceValue load) {
		this.load = load;
	}

	/**
	 * Provides torque.
	 *
	 * @return the torque
	 */
	public TorqueValue getTorque() {
		return torque;
	}

	/**
	 * Sets torque.
	 *
	 * @param torque the torque to set
	 */
	public void setMoment(TorqueValue torque) {
		this.torque = torque;
	}

	/**
	 * Provides distributed load.
	 *
	 * @return the distributedLoad
	 */
	public ForceDensityValue getDistributedLoad() {
		return distributedLoad;
	}

	/**
	 * Sets distributed load.
	 *
	 * @param distributedLoad the distributedLoad to set
	 */
	public void setDistributedLoad(ForceDensityValue distributedLoad) {
		this.distributedLoad = distributedLoad;
	}

	/**
	 * Returns <code>true</code> if there is joint at this section.
	 *
	 * @return the joint
	 */
	public boolean isJoint() {
		return joint;
	}

	/**
	 * Sets joint.
	 *
	 * @param joint the joint to set
	 */
	public void setJoint(boolean joint) {
		this.joint = joint;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getWidth() {
		return 1.0;
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
		put(LOAD, polynom.polynomialDerivative());
		double position = previous != null
				? previous.getLength().getSI()
				: 0;
		double a0 = previous != null ? previous.getValue(ANGLE, position) : 0;
		PolynomialFunction p = PolynomialUtil.divide(polynom,
				getYoung().getSI() * inertiaMoment.getSI());
		PolynomialFunction angle = PolynomialUtil.integrate(p, a0);
		put(ANGLE, angle);
		double v0 = previous != null
				? previous.getValue(DEFLECTION, position)
				: 0;
		PolynomialFunction deflection = PolynomialUtil.integrate(angle, v0);
		put(DEFLECTION, deflection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		final String colon = ": ";
		final String semicolon = "; ";
		StringBuilder buffer = new StringBuilder();
		if (joint) {
			buffer.append(TextUtil.get("type.joint")).append(semicolon);
		}
		buffer.append(TextUtil.get("title.length")).append(colon)
				.append(toString(getLength(), Length.UNIT)).append(semicolon);
		if (load != null) {
			buffer.append(TextUtil.get("entity.load")).append(colon)
					.append(toString(load, ForceValue.UNIT)).append(semicolon);
		}
		if (torque != null) {
			buffer.append(TextUtil.get("entity.moment")).append(colon)
					.append(toString(torque, TorqueValue.UNIT))
					.append(semicolon);
		}
		if (distributedLoad != null) {
			buffer.append(TextUtil.get("entity.load.distributed")).append(colon)
					.append(toString(distributedLoad, ForceDensityValue.UNIT))
					.append(semicolon);
		}
		return buffer.toString();
	}
}
