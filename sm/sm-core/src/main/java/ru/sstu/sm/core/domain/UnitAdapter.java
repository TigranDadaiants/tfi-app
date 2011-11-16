package ru.sstu.sm.core.domain;

import java.util.HashMap;
import java.util.Map;

import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * <code>UnitAdapter</code> class is generic {@link XmlAdapter} for different
 * units.
 *
 * @author Denis_Murashev
 *
 * @param <T> type of quantity
 * @since SM 3.0
 */
class UnitAdapter<T extends Quantity> extends XmlAdapter<String, Unit<T>> {

	/**
	 * Available units for given {@link Quantity}.
	 */
	private final Map<String, Unit<T>> units = new HashMap<String, Unit<T>>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String marshal(Unit<T> v) {
		return v.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Unit<T> unmarshal(String v) {
		return units.get(v);
	}

	/**
	 * Registers new unit.
	 *
	 * @param unit unit
	 */
	protected final void addUnit(Unit<T> unit) {
		units.put(unit.toString(), unit);
	}
}
