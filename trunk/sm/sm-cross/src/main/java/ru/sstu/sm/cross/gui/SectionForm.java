package ru.sstu.sm.cross.gui;

import javax.swing.JCheckBox;

import ru.sstu.sm.core.domain.ForceDensityValue;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.InertiaMomentValue;
import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.core.gui.forms.AbstractForm;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.cross.domain.SimpleSection;

/**
 * <code>SectionForm</code> class is form for section editing.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class SectionForm extends AbstractForm {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -1704975393642621569L;

	/**
	 * Default Young module value.
	 */
	private static final double YOUNG = 2e5;

	/**
	 * Default cross section inertia moment.
	 */
	private static final double INERTIA_MOMENT = 1e-4;

	/**
	 * Check box for joint.
	 */
	private JCheckBox jointCheckBox = new JCheckBox(TextUtil.get("type.joint"));

	/**
	 * Initializes section form.
	 *
	 * @param section section
	 */
	public SectionForm(SimpleSection section) {
		LengthValue length = new LengthValue();
		PressureValue young = new PressureValue(YOUNG);
		InertiaMomentValue crossSection
				= new InertiaMomentValue(INERTIA_MOMENT);
		ForceValue load = new ForceValue();
		TorqueValue torque = new TorqueValue();
		ForceDensityValue distributedLoad = new ForceDensityValue();
		if (section != null) {
			length = section.getLength();
			young = section.getYoung();
			crossSection = section.getCrossSection();
			load = section.getLoad();
			torque = section.getTorque();
			distributedLoad = section.getDistributedLoad();
			jointCheckBox.setSelected(section.isJoint());
		}
		addValue(TextUtil.get("title.length"), length);
		addValue(TextUtil.get("title.young"), young);
		addValue(TextUtil.get("entity.inertia.moment"), crossSection);
		addValue(TextUtil.get("entity.load"), load, false);
		addValue(TextUtil.get("entity.moment"), torque, false);
		addValue(TextUtil.get("entity.load.distributed"), distributedLoad,
				false);
		addComponent(jointCheckBox);
	}

	/**
	 * Provides form title.
	 *
	 * @return title of the dialog
	 */
	public String getTitle() {
		return TextUtil.get("title.section");
	}

	/**
	 * Provides result.
	 *
	 * @return result entity of dialog
	 */
	public SimpleSection getResult() {
		final int lengthIndex = 0;
		final int youngIndex = 1;
		final int crossSectionIndex = 2;
		final int loadIndex = 3;
		final int torqueIndex = 4;
		final int distributedLoadIndex = 5;
		return new SimpleSection((LengthValue) getValue(lengthIndex),
				(PressureValue) getValue(youngIndex),
				(InertiaMomentValue) getValue(crossSectionIndex),
				(ForceValue) getValue(loadIndex),
				(TorqueValue) getValue(torqueIndex),
				(ForceDensityValue) getValue(distributedLoadIndex),
				jointCheckBox.isSelected());
	}
}
