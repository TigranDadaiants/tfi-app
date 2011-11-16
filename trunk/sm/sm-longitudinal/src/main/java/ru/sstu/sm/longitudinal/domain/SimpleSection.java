package ru.sstu.sm.longitudinal.domain;

import javax.measure.quantity.Length;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.log4j.Logger;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.domain.AreaValue;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>SimpleSection</code> class is {@link Section} implementation for
 * Longitudinal task.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public final class SimpleSection extends Section {

	/**
	 * Load.
	 */
	public static final int LOAD = 0;

	/**
	 * Tension.
	 */
	public static final int TENSION = 1;

	/**
	 * Extension.
	 */
	public static final int EXTENSION = 2;

	/**
	 * Total extension.
	 */
	public static final int TOTAL = 3;

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1967600656046818013L;

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(SimpleSection.class);

	/**
	 * Cross section of section.
	 */
	@XmlElement
	private AreaValue crossSection;

	/**
	 * Load at section.
	 */
	@XmlElement
	private ForceValue load;

	/**
	 * Just for JAXB.
	 */
	public SimpleSection() {
	}

	/**
	 * Initializes simple section.
	 *
	 * @param crossSection cross section
	 * @param length       length
	 * @param young        Young module
	 * @param load         load
	 */
	public SimpleSection(AreaValue crossSection, LengthValue length,
			PressureValue young, ForceValue load) {
		setLength(length);
		setYoung(young);
		this.crossSection = crossSection;
		this.load = load;
		put(LOAD, PolynomialUtil.create());
		put(TENSION, PolynomialUtil.create());
		put(EXTENSION, PolynomialUtil.create());
		put(TOTAL, PolynomialUtil.create());
	}

	/**
	 * Provides cross section.
	 *
	 * @return cross section
	 */
	public AreaValue getCrossSection() {
		return crossSection;
	}

	/**
	 * Sets cross section.
	 *
	 * @param crossSection cross section
	 */
	public void setCrossSection(AreaValue crossSection) {
		this.crossSection = crossSection;
	}

	/**
	 * @return the load
	 */
	public ForceValue getLoad() {
		return load;
	}

	/**
	 * @param load the load to set
	 */
	public void setLoad(ForceValue load) {
		this.load = load;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getWidth() {
		return crossSection.getSI();
	}

	/**
	 * Updates default expression with given polynomial function.
	 *
	 * @param polynom  polynomial function
	 * @param previous previous section
	 */
	@Override
	public void update(PolynomialFunction polynom, Section previous) {
		log.debug("Updating section:");
		put(LOAD, polynom);
		log.debug("Load: " + get(LOAD));
		double cs = getCrossSection().getSI();
		put(TENSION, PolynomialUtil.divide(polynom, cs));
		log.debug("Tension: " + get(TENSION));
		double factor = cs * getYoung().getSI();
		PolynomialFunction p = PolynomialUtil.divide(polynom, factor);
		PolynomialFunction extension = PolynomialUtil.integrate(p, 0.0);
		put(EXTENSION, extension);
		log.debug("Extension: " + get(EXTENSION));
		double position = previous != null
				? previous.getLength().getSI()
				: 0;
		double e = previous != null
				? previous.getValue(TOTAL, position)
				: 0;
		p = PolynomialUtil.divide(polynom, factor);
		PolynomialFunction total = PolynomialUtil.integrate(p, e);
		put(TOTAL, total);
		log.debug("Total Extension: " + get(TOTAL));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		final String colon = ": ";
		final String semicolon = "; ";
		StringBuilder buffer = new StringBuilder();
		buffer.append(TextUtil.get("entity.crosssection")).append(colon)
				.append(toString(crossSection, AreaValue.UNIT))
				.append(semicolon);
		buffer.append(TextUtil.get("title.length")).append(colon)
				.append(toString(getLength(), Length.UNIT)).append(semicolon);
		buffer.append(TextUtil.get("title.young")).append(colon)
				.append(toString(getYoung(), PressureValue.UNIT))
				.append(semicolon);
		buffer.append(TextUtil.get("title.load")).append(colon)
				.append(toString(load, ForceValue.UNIT));
		return buffer.toString();
	}
}
