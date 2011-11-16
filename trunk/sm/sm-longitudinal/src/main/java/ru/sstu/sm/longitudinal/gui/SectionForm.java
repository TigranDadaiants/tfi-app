package ru.sstu.sm.longitudinal.gui;

import ru.sstu.sm.core.domain.AreaValue;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.gui.forms.AbstractForm;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.longitudinal.domain.SimpleSection;

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
	private static final long serialVersionUID = 5185956819913353590L;

	/**
	 * Default Young module value.
	 */
	private static final double YOUNG = 2e5;

	/**
	 * Initializes section form.
	 *
	 * @param section section
	 */
	public SectionForm(SimpleSection section) {
		AreaValue crossSection = new AreaValue();
		LengthValue length = new LengthValue();
		PressureValue young = new PressureValue(YOUNG);
		ForceValue load = new ForceValue();
		if (section != null) {
			crossSection = section.getCrossSection();
			length = section.getLength();
			young = section.getYoung();
			load = section.getLoad();
		}
		addValue(TextUtil.get("entity.crosssection"), crossSection);
		addValue(TextUtil.get("title.length"), length);
		addValue(TextUtil.get("title.young"), young);
		addValue(TextUtil.get("title.load"), load);
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
		final int crossSection = 0;
		final int length = 1;
		final int young = 2;
		final int load = 3;
		return new SimpleSection((AreaValue) getValue(crossSection),
				(LengthValue) getValue(length), (PressureValue) getValue(young),
				(ForceValue) getValue(load));
	}
}
