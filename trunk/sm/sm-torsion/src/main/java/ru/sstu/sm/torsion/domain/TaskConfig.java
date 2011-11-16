package ru.sstu.sm.torsion.domain;

import java.util.List;

import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Torque;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Direction;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.TorqueValue;

/**
 * <code>TaskConfig</code> class contains configuration information.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
@XmlRootElement(name = "config")
@XmlAccessorType(XmlAccessType.NONE)
public final class TaskConfig extends Config<AbstractSection> {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 835112835858459097L;

	/**
	 * Limit.
	 */
	@XmlElement
	private PressureValue limit = new PressureValue();

	/**
	 * Initializes task configuration.
	 */
	public TaskConfig() {
		setDirection(Direction.RIGHT);
		setType(Type.FREE);
		setConverter(AbstractSection.TORQUE,
				Torque.UNIT.getConverterTo(TorqueValue.UNIT));
		setConverter(AbstractSection.ANGLE,
				Angle.UNIT.getConverterTo(Angle.UNIT));
		setConverter(AbstractSection.TOTAL,
				Angle.UNIT.getConverterTo(Angle.UNIT));
		// TODO Correct Inertia values
		setConverter(AbstractSection.INERTIA,
				Angle.UNIT.getConverterTo(Angle.UNIT));
		setConverter(AbstractSection.MINIMAL_SIZE,
				Length.UNIT.getConverterTo(Length.UNIT));
	}

	/**
	 * Provides limit.
	 *
	 * @return limit tension
	 */
	public PressureValue getLimit() {
		return limit;
	}

	/**
	 * Sets limit.
	 *
	 * @param limit limit
	 */
	public void setLimit(PressureValue limit) {
		this.limit = limit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getModule() {
		return "sm-torsion";
	}

	/**
	 * {@inheritDoc}
	 */
	@XmlElementWrapper(name = "sections")
	@XmlElements({
		@XmlElement(name = "annularSection", type = AnnularSection.class),
		@XmlElement(name = "circularSection", type = CircularSection.class),
		@XmlElement(name = "rectangularSection",
				type = RectangularSection.class)
	})
	@Override
	protected List<AbstractSection> getSections() {
		return super.getSections();
	}
}
