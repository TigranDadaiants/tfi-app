package ru.sstu.sm.core.domain;

import javax.measure.quantity.Torque;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <code>TorqueValue</code> class represents external torque.
 *
 * @author Denis A. Murashev
 * @since SM 3.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class TorqueValue extends Value<Torque> {

	/**
	 * Default unit for torque.
	 */
	public static final Unit<Torque> UNIT = SI.KILO(Torque.UNIT);

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 2555140121803416275L;

	/**
	 * Just for JAXB.
	 */
	public TorqueValue() {
		super(0.0, UNIT);
	}

	/**
	 * @param value value
	 */
	public TorqueValue(double value) {
		super(value, UNIT);
	}

	/**
	 * {@inheritDoc}
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(value = TorqueAdapter.class)
	@Override
	public Unit<Torque> getUnit() {
		return super.getUnit();
	}

	/**
	 * XML adapter for {@link Torque} values.
	 *
	 * @author Denis_Murashev
	 */
	private static final class TorqueAdapter extends UnitAdapter<Torque> {

		/**
		 * Initializes adapter.
		 */
		private TorqueAdapter() {
			addUnit(UNIT);
		}
	}
}
