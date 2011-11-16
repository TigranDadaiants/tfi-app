package ru.sstu.sm.core.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>Section</code> class represents abstract section in 1 dimensional task.
 *
 * @author Denis_Murashev
 * @since SM 2.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Section implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -8981358602083507210L;

	/**
	 * Young module.
	 */
	@XmlElement
	private PressureValue young;

	/**
	 * Length.
	 */
	@XmlElement
	private LengthValue length;

	/**
	 * Solution data holder.
	 */
	private Map<Integer, PolynomialFunction> data
			= new HashMap<Integer, PolynomialFunction>();

	/**
	 * @return Young module
	 */
	public final PressureValue getYoung() {
		return young;
	}

	/**
	 * Sets Young module.
	 *
	 * @param young Young module value
	 */
	public final void setYoung(PressureValue young) {
		this.young = young;
	}

	/**
	 * Provides section's length.
	 *
	 * @return the length
	 */
	public LengthValue getLength() {
		return length;
	}

	/**
	 * Sets section's length.
	 *
	 * @param length the length to set
	 */
	public void setLength(LengthValue length) {
		this.length = length;
	}

	/**
	 * Provides polynomial function of given type.
	 *
	 * @param type type
	 * @return polynomial function
	 */
	public final PolynomialFunction get(int type) {
		return data.get(type);
	}

	/**
	 * @param type     type
	 * @param function function
	 */
	public final void put(int type, PolynomialFunction function) {
		data.put(type, function);
	}

	/**
	 * Provides value of given type at given point.
	 *
	 * @param type     type
	 * @param position position
	 * @return value of given type for the given point
	 */
	public final double getValue(int type, double position) {
		return data.get(type).value(position);
	}

	/**
	 * Provides section's width (in any values).
	 *
	 * @return the width
	 */
	public abstract double getWidth();

	/**
	 * Updates default expression with given polynomial function.
	 *
	 * @param polynom  polynomial function
	 * @param previous previous section
	 */
	public abstract void update(PolynomialFunction polynom, Section previous);

	/**
	 * Provides localized text for given value and unit.
	 *
	 * @param <T>   type
	 * @param value value
	 * @param unit  unit
	 * @return localized message
	 * @since SM 3.0
	 */
	protected <T extends Quantity> String toString(Value<T> value,
			Unit<T> unit) {
		return value.doubleValue(unit) + " " + TextUtil.get(unit);
	}
}
