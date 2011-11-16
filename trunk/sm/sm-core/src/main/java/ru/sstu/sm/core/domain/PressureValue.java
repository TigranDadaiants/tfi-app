package ru.sstu.sm.core.domain;

import javax.measure.quantity.Pressure;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <code>PressureValue</code> represents measurable value for pressure.
 *
 * @author Denis A. Murashev
 * @since SM 3.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class PressureValue extends Value<Pressure> {

	/**
	 * Default unit for Young module.
	 */
	public static final Unit<Pressure> UNIT = SI.MEGA(Pressure.UNIT);

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1380666375503655667L;

	/**
	 * Just needed for XML binding.
	 */
	public PressureValue() {
		super(0.0, UNIT);
	}

	/**
	 * @param value value
	 */
	public PressureValue(double value) {
		super(value, UNIT);
	}

	/**
	 * @param value value
	 * @param unit  unit
	 */
	public PressureValue(double value, Unit<Pressure> unit) {
		super(value, unit);
	}

	/**
	 * {@inheritDoc}
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(value = PressureAdapter.class)
	@Override
	public Unit<Pressure> getUnit() {
		return super.getUnit();
	}

	/**
	 * XML adapter for {@link Pressure} values.
	 *
	 * @author Denis_Murashev
	 */
	private static final class PressureAdapter extends UnitAdapter<Pressure> {

		/**
		 * Initializes adapter.
		 */
		private PressureAdapter() {
			addUnit(UNIT);
		}
	}
}
