package ru.sstu.sm.core.domain;

import javax.measure.quantity.Force;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <code>ForceValue</code> class represents measurable external force.
 *
 * @author Denis A. Murashev
 * @since SM 3.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class ForceValue extends Value<Force> {

	/**
	 * Default unit for load.
	 */
	public static final Unit<Force> UNIT = SI.KILO(Force.UNIT);

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 3584789121939592634L;

	/**
	 * Just needed for XML binding.
	 */
	public ForceValue() {
		super(0.0, UNIT);
	}

	/**
	 * @param value value
	 */
	public ForceValue(double value) {
		super(value, UNIT);
	}

	/**
	 * @param value value
	 * @param unit  unit
	 */
	public ForceValue(double value, Unit<Force> unit) {
		super(value, unit);
	}

	/**
	 * {@inheritDoc}
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(value = ForceAdapter.class)
	@Override
	public Unit<Force> getUnit() {
		return super.getUnit();
	}

	/**
	 * XML adapter for {@link Force} values.
	 *
	 * @author Denis_Murashev
	 */
	private static final class ForceAdapter extends UnitAdapter<Force> {

		/**
		 * Initializes adapter.
		 */
		private ForceAdapter() {
			addUnit(UNIT);
		}
	}
}
