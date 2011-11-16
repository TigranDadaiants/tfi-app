package ru.sstu.sm.core.domain;

import javax.measure.quantity.Quantity;
import javax.measure.unit.ProductUnit;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <code>InertiaMomentValue</code> class represents value for cross section
 * moment of inertia value.
 *
 * @author Denis A. Murashev
 * @since SM 3.0
 */
public class InertiaMomentValue
		extends Value<InertiaMomentValue.InertiaMoment> {

	/**
	 * Cross section inertia.
	 */
	public static final Unit<InertiaMoment> UNIT
		= new ProductUnit<InertiaMoment>(SI.SQUARE_METER.pow(2));

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 8677746006372899822L;

	/**
	 * Just for JAXB.
	 */
	public InertiaMomentValue() {
		super(0.0, UNIT);
	}

	/**
	 * @param density density
	 */
	public InertiaMomentValue(double density) {
		super(density, UNIT);
	}

	/**
	 * @param value value
	 * @param unit  unit
	 */
	public InertiaMomentValue(double value, Unit<InertiaMoment> unit) {
		super(value, unit);
	}

	/**
	 * {@inheritDoc}
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(value = InertiaMomentAdapter.class)
	@Override
	public Unit<InertiaMoment> getUnit() {
		return super.getUnit();
	}

	/**
	 * Cross section inertia.
	 */
	public interface InertiaMoment extends Quantity {
	}

	/**
	 * XML adapter for {@link InertiaMoment} values.
	 *
	 * @author Denis_Murashev
	 */
	private static final class InertiaMomentAdapter
			extends UnitAdapter<InertiaMoment> {

		/**
		 * Initializes adapter.
		 */
		private InertiaMomentAdapter() {
			addUnit(UNIT);
		}
	}
}
