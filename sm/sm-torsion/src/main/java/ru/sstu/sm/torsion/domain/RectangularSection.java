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
 * <code>RectangularSection</code> class represents section with rectangular
 * cross section.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class RectangularSection extends AbstractSection {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -4642561795751374069L;

	/**
	 * Helpers.
	 */
	private static final Helper[] HELPERS = {
		new Helper(10.0, 3.123, 3.123),
		new Helper(8.0, 2.456, 2.456),
		new Helper(6.0, 1.789, 1.789),
		new Helper(5.0, 1.455, 1.455),
		new Helper(4.0, 1.123, 1.128),
		new Helper(3.0, 0.790, 0.801),
		new Helper(2.5, 0.622, 0.645),
		new Helper(2.0, 0.457, 0.493),
		new Helper(1.75, 0.375, 0.418),
		new Helper(1.5, 0.294, 0.346),
		new Helper(1.2, 0.199, 0.263),
		new Helper(1.0, 0.141, 0.208),
	};

	/**
	 * Bigger size.
	 */
	@XmlElement
	private LengthValue biggerSize;

	/**
	 * Smaller size.
	 */
	@XmlElement
	private LengthValue smallerSize;

	/**
	 * Just for JAXB.
	 */
	public RectangularSection() {
	}

	/**
	 * Initializes section.
	 *
	 * @param width  width
	 * @param height height
	 * @param length length
	 * @param young  Young module
	 * @param torque torque
	 */
	public RectangularSection(LengthValue width, LengthValue height,
			LengthValue length, PressureValue young, TorqueValue torque) {
		super(length, young, torque);
		if (width.getSI() >= height.getSI()) {
			biggerSize = width;
			smallerSize = height;
		} else {
			biggerSize = height;
			smallerSize = width;
		}
	}

	/**
	 * Provides bigger size.
	 *
	 * @return bigger size
	 */
	public LengthValue getBiggerSize() {
		return biggerSize;
	}

	/**
	 * Sets bigger size.
	 *
	 * @param biggerSize bigger size
	 */
	public void setBiggerSize(LengthValue biggerSize) {
		this.biggerSize = biggerSize;
	}

	/**
	 * Provides smaller size.
	 *
	 * @return smaller size
	 */
	public LengthValue getSmallerSize() {
		return smallerSize;
	}

	/**
	 * Sets smaller size.
	 *
	 * @param smallerSize smaller size
	 */
	public void setSmallerSize(LengthValue smallerSize) {
		this.smallerSize = smallerSize;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getWidth() {
		return biggerSize.getSI();
	}

	/**
	 * Provides inertia.
	 *
	 * @return the moment of inertia for the section
	 */
	public double getInertia() {
		double b = smallerSize.getSI();
		double r = biggerSize.getSI() / b;
		double b4 = b * b * b * b;
		for (Helper helper : HELPERS) {
			if (r >= helper.value) {
				return helper.inertia * b4;
			}
		}
		return 0.0;
	}

	/**
	 * Provides minimal possible size for section.
	 *
	 * @param limitValue limit value for tension
	 * @return limit size for given limit tension value
	 */
	public double getMinimalSize(double limitValue) {
		double torque = getValue(TORQUE, 0.0);
		double b = smallerSize.getSI();
		double r = biggerSize.getSI() / b;
		double beta = 0.0;
		for (Helper helper : HELPERS) {
			if (r >= helper.value) {
				beta = helper.beta;
			}
		}
		return Math.cbrt(Math.abs(torque) / beta / limitValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(TextUtil.get("type.rectangular")).append(". ");
		buffer.append(TextUtil.get("entity.crosssection")).append(": ")
				.append(toString(biggerSize, SI.MILLIMETER)).append(" x ")
				.append(toString(smallerSize, SI.MILLIMETER)).append("; ");
		buffer.append(super.toString());
		return buffer.toString();
	}

	/**
	 * Helper for rectangular section.
	 *
	 * @author Denis_Murashev
	 */
	private static final class Helper {

		/**
		 * Value.
		 */
		private double value;

		/**
		 * Inertia.
		 */
		private double inertia;

		/**
		 * Beta.
		 */
		private double beta;

		/**
		 * @param value   value
		 * @param inertia inertia
		 * @param beta    beta
		 */
		private Helper(double value, double inertia, double beta) {
			this.value = value;
			this.inertia = inertia;
			this.beta = beta;
		}
	}
}
