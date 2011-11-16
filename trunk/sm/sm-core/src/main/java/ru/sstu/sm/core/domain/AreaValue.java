package ru.sstu.sm.core.domain;

import javax.measure.quantity.Area;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <code>AreaValue</code> represents measurable area.
 *
 * @author Denis A. Murashev
 * @since SM 3.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class AreaValue extends Value<Area> {

	/**
	 * Default unit for area.
	 */
	public static final Unit<Area> UNIT = SI.CENTI(SI.CENTI(Area.UNIT));

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -8197065152802541748L;

	/**
	 * Just needed for XML binding.
	 */
	public AreaValue() {
		super(0.0, UNIT);
	}

	/**
	 * @param value value
	 */
	public AreaValue(double value) {
		super(value, UNIT);
	}

	/**
	 * @param value value
	 * @param unit  unit
	 */
	public AreaValue(double value, Unit<Area> unit) {
		super(value, unit);
	}

	/**
	 * {@inheritDoc}
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(value = AreaAdapter.class)
	@Override
	public Unit<Area> getUnit() {
		return super.getUnit();
	}

	/**
	 * XML adapter for {@link Area} values.
	 *
	 * @author Denis_Murashev
	 */
	private static final class AreaAdapter extends UnitAdapter<Area> {

		/**
		 * Initializes adapter.
		 */
		private AreaAdapter() {
			addUnit(UNIT);
		}
	}
}
