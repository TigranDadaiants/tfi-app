package ru.sstu.sm.core.domain;

import javax.measure.quantity.Quantity;
import javax.measure.unit.ProductUnit;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <code>ForceDensityValue</code> class represents value for external
 * distributed force.
 *
 * @author Denis A. Murashev
 * @since SM 3.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class ForceDensityValue
		extends Value<ForceDensityValue.ForceDensity> {

	/**
	 * Linear of force unit.
	 */
	public static final Unit<ForceDensity> UNIT
		= new ProductUnit<ForceDensity>(SI.KILO(SI.NEWTON).divide(SI.METER));

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 6129055673191535032L;

	/**
	 * Just for JAXB.
	 */
	public ForceDensityValue() {
		super(0.0, UNIT);
	}

	/**
	 * @param density density
	 */
	public ForceDensityValue(double density) {
		super(density, UNIT);
	}

	/**
	 * @param value value
	 * @param unit  unit
	 */
	public ForceDensityValue(double value, Unit<ForceDensity> unit) {
		super(value, unit);
	}

	/**
	 * {@inheritDoc}
	 */
	@XmlAttribute
	@XmlJavaTypeAdapter(value = ForceDensityAdapter.class)
	@Override
	public Unit<ForceDensity> getUnit() {
		return super.getUnit();
	}

	/**
	 * Linear density of force.
	 */
	public interface ForceDensity extends Quantity {
	}

	/**
	 * XML adapter for {@link ForceDensity} values.
	 *
	 * @author Denis_Murashev
	 */
	private static final class ForceDensityAdapter
			extends UnitAdapter<ForceDensity> {

		/**
		 * Initializes adapter.
		 */
		private ForceDensityAdapter() {
			addUnit(UNIT);
		}
	}
}
