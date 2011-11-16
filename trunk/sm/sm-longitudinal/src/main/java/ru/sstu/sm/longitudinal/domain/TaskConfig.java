package ru.sstu.sm.longitudinal.domain;

import java.util.List;

import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Pressure;
import javax.measure.unit.SI;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Direction;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;

/**
 * <code>TaskConfig</code> class is concrete configuration for Longitudinal
 * task.
 *
 * @author Denis Murashev
 * @since SM 1.0
 */
@XmlRootElement(name = "config")
@XmlAccessorType(XmlAccessType.NONE)
public final class TaskConfig extends Config<SimpleSection> {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -5094820994974465895L;

	/**
	 * Delta.
	 */
	@XmlElement
	private LengthValue delta = new LengthValue();

	/**
	 * Initializes configuration.
	 */
	public TaskConfig() {
		setDirection(Direction.UP);
		setType(Type.FREE);
		setConverter(SimpleSection.LOAD,
				Force.UNIT.getConverterTo(ForceValue.UNIT));
		setConverter(SimpleSection.TENSION,
				Pressure.UNIT.getConverterTo(PressureValue.UNIT));
		setConverter(SimpleSection.EXTENSION,
				Length.UNIT.getConverterTo(SI.MILLIMETER));
		setConverter(SimpleSection.TOTAL,
				Length.UNIT.getConverterTo(SI.MILLIMETER));
	}

	/**
	 * Provides delta.
	 *
	 * @return delta for hard type task
	 */
	public LengthValue getDelta() {
		return delta;
	}

	/**
	 * Sets delta.
	 *
	 * @param delta delta
	 */
	public void setDelta(LengthValue delta) {
		this.delta = delta;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getModule() {
		return "sm-longitudinal";
	}

	/**
	 * {@inheritDoc}
	 */
	@XmlElementWrapper(name = "sections")
	@XmlElement(name = "section")
	@Override
	protected List<SimpleSection> getSections() {
		return super.getSections();
	}
}
