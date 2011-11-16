package ru.sstu.sm.core.domain;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * <code>Value</code> describes abstract measurable value.
 *
 * @author Denis Murashev
 * @param <T> type of quantity
 * @since SM 3.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Value<T extends Quantity> extends Measure<T>
		implements Quantity {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 2268276240835351969L;

	/**
	 * Holds concrete value.
	 */
	@XmlAttribute
	private double value;

	/**
	 * Holds value unit.
	 */
	private Unit<T> unit;

	/**
	 * Just needed for XML binding.
	 */
	protected Value() {
	}

	/**
	 * @param value value
	 * @param unit  unit
	 */
	protected Value(double value, Unit<T> unit) {
		this.value = value;
		this.unit = unit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Number getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	public Unit<T> getUnit() {
		return unit;
	}

	/**
	 * {@inheritDoc}
	 */
	public double doubleValue(Unit<T> qUnit) {
		return getUnit().getConverterTo(qUnit).convert(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal decimalValue(Unit<T> qUnit, MathContext ctx) {
		return getUnit().getConverterTo(qUnit)
				.convert(new BigDecimal(value), ctx);
	}

	/**
	 * Provides value in SI.
	 *
	 * @return SI value
	 */
	public double getSI() {
		return toSI().getValue().doubleValue();
	}
}
