package ru.sstu.sm.cross.domain;

import java.util.List;

import javax.measure.quantity.Angle;
import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Torque;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Direction;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.TorqueValue;

/**
 * <code>TaskConfig</code> class is concrete configuration for task.
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
	private static final long serialVersionUID = -7277413559885589877L;

	/**
	 * Joint section.
	 */
	private SimpleSection jointSection;

	/**
	 * Initializes configuration.
	 */
	public TaskConfig() {
		setDirection(Direction.RIGHT);
		setConverter(SimpleSection.LOAD,
				Force.UNIT.getConverterTo(ForceValue.UNIT));
		setConverter(SimpleSection.TORQUE,
				Torque.UNIT.getConverterTo(TorqueValue.UNIT));
		setConverter(SimpleSection.ANGLE,
				Angle.UNIT.getConverterTo(Angle.UNIT));
		setConverter(SimpleSection.DEFLECTION,
				Length.UNIT.getConverterTo(Length.UNIT));
	}

	/**
	 * Provides section with joint.
	 *
	 * @return joint reaction
	 */
	public SimpleSection getJointSection() {
		return jointSection;
	}

	/**
	 * Sets joint section.
	 *
	 * @param jointSection new joint section
	 */
	public void setJointSection(SimpleSection jointSection) {
		if (this.jointSection != null) {
			this.jointSection.setLoad(null);
			this.jointSection.setJoint(false);
		}
		this.jointSection = jointSection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getModule() {
		return "sm-cross";
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
