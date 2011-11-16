package ru.sstu.sm.core.domain;

import javax.measure.quantity.Length;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <code>LengthValue</code> represents measurable length.
 *
 * @author Denis A. Murashev
 * @since SM 3.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class LengthValue extends Value<Length> {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -7149386326883598575L;

	/**
	 * Just needed for XML binding.
	 */
	public LengthValue() {
		super(0.0, Length.UNIT);
	}

	/**
	 * @param value value
	 */
	public LengthValue(double value) {
		super(value, Length.UNIT);
	}

	/**
	 * @param value value
	 * @param unit  unit
	 */
	public LengthValue(double value, Unit<Length> unit) {
		super(value, unit);
	}

	/**
	 * {@inheritDoc}
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(value = LengthAdapter.class)
	@Override
	public Unit<Length> getUnit() {
		return super.getUnit();
	}

	/**
	 * XML adapter for {@link Length} values.
	 *
	 * @author Denis_Murashev
	 */
	private static final class LengthAdapter extends UnitAdapter<Length> {

		/**
		 * Initializes adapter.
		 */
		private LengthAdapter() {
			addUnit(SI.METER);
			addUnit(SI.CENTIMETER);
			addUnit(SI.MILLIMETER);
		}
	}
}
