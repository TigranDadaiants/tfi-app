package ru.sstu.sm.torsion.gui;

import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.measure.unit.SI;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.core.gui.forms.AbstractForm;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.torsion.domain.AbstractSection;
import ru.sstu.sm.torsion.domain.AnnularSection;
import ru.sstu.sm.torsion.domain.CircularSection;
import ru.sstu.sm.torsion.domain.RectangularSection;

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
	private static final long serialVersionUID = -1705534122194531584L;

	/**
	 * Default Young module value.
	 */
	private static final double DEFAULT_YOUNG = 8e4;

	/**
	 * Default size.
	 */
	private static final int DEFAULT_SIZE = 100;

	/**
	 * Length index.
	 */
	private static final int LENGTH = 2;

	/**
	 * Young module index.
	 */
	private static final int YOUNG = 3;

	/**
	 * Moment index.
	 */
	private static final int TORQUE = 4;

	/**
	 * Current helper.
	 */
	private Helper current;

	/**
	 * Initializes form.
	 *
	 * @param section section
	 */
	public SectionForm(AbstractSection section) {
		Map<Class<? extends AbstractSection>, Helper> helpers
				= new HashMap<Class<? extends AbstractSection>, Helper>();
		helpers.put(CircularSection.class, new CircularForm());
		helpers.put(AnnularSection.class, new AnnularForm());
		helpers.put(RectangularSection.class, new RectangularForm());

		current = helpers.get(CircularSection.class);
		LengthValue first = new LengthValue(DEFAULT_SIZE, SI.MILLIMETER);
		LengthValue second = new LengthValue(DEFAULT_SIZE / 2, SI.MILLIMETER);
		LengthValue length = new LengthValue();
		PressureValue young = new PressureValue(DEFAULT_YOUNG);
		TorqueValue torque = new TorqueValue();

		if (section != null) {
			current = helpers.get(section.getClass());
			length = section.getLength();
			young = section.getYoung();
			torque = section.getTorque();
		}
		addValue("", first);
		addValue("", second);
		addValue(TextUtil.get("title.length"), length);
		addValue(TextUtil.get("title.young"), young);
		addValue(TextUtil.get("entity.moment"), torque);
		changeType(section);

		final JComboBox formSelector = new JComboBox(helpers.values()
				.toArray());
		formSelector.setSelectedItem(current);
		formSelector.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				current = (Helper) formSelector.getSelectedItem();
				changeType(null);
			}
		});
		final int gridy = 5;
		add(new JLabel(TextUtil.get("type.section")),
				new GridBagConstraints(0, gridy, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.BOTH, INSETS,
				0, 0));
		add(formSelector, new GridBagConstraints(1, gridy, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, INSETS,
				0, 0));
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
	public AbstractSection getResult() {
		return current.getResult();
	}

	/**
	 * @param section section
	 */
	private void changeType(AbstractSection section) {
		current.update(section);
	}

	/**
	 * Helper.
	 *
	 * @author Denis_Murashev
	 */
	private interface Helper {

		/**
		 * @param section section
		 */
		void update(AbstractSection section);

		/**
		 * @return section
		 */
		AbstractSection getResult();
	}

	/**
	 * Circular helper.
	 *
	 * @author Denis_Murashev
	 */
	private final class CircularForm implements Helper {

		/**
		 * Diameter index.
		 */
		private static final int DIAMETER = 0;

		/**
		 * Empty index.
		 */
		private static final int EMPTY = 1;

		/**
		 * {@inheritDoc}
		 */
		public void update(AbstractSection section) {
			SectionForm.this.setEnabled(EMPTY, false);
			SectionForm.this.setDescription(DIAMETER,
					TextUtil.get("title.diameter"));
			SectionForm.this.setDescription(EMPTY, "");
			if (section != null) {
				SectionForm.this.setValue(DIAMETER,
						((CircularSection) section).getDiameter());
			}
		}

		/**
		 * {@inheritDoc}
		 */
		public AbstractSection getResult() {
			return new CircularSection((LengthValue) getValue(DIAMETER),
					(LengthValue) getValue(LENGTH),
					(PressureValue) getValue(YOUNG),
					(TorqueValue) getValue(TORQUE));
		}

		/**
		 * {@inheritDoc}
		 */
		public String toString() {
			return TextUtil.get("type.circular");
		}
	}

	/**
	 * Annular helper.
	 *
	 * @author Denis_Murashev
	 */
	private final class AnnularForm implements Helper {

		/**
		 * External diameter index.
		 */
		private static final int EXTERNAL_DIAMETER = 0;

		/**
		 * Internal diameter index.
		 */
		private static final int INTERNAL_DIAMETER = 1;

		/**
		 * {@inheritDoc}
		 */
		public void update(AbstractSection section) {
			SectionForm.this.setEnabled(INTERNAL_DIAMETER, true);
			SectionForm.this.setDescription(EXTERNAL_DIAMETER,
					TextUtil.get("title.diameter.external"));
			SectionForm.this.setDescription(INTERNAL_DIAMETER,
					TextUtil.get("title.diameter.internal"));
			if (section != null) {
				SectionForm.this.setValue(EXTERNAL_DIAMETER,
						((AnnularSection) section).getExternalDiameter());
				SectionForm.this.setValue(INTERNAL_DIAMETER,
						((AnnularSection) section).getInternalDiameter());
			}
		}

		/**
		 * {@inheritDoc}
		 */
		public AbstractSection getResult() {
			return new AnnularSection((LengthValue) getValue(EXTERNAL_DIAMETER),
					(LengthValue) getValue(INTERNAL_DIAMETER),
					(LengthValue) getValue(LENGTH),
					(PressureValue) getValue(YOUNG),
					(TorqueValue) getValue(TORQUE));
		}

		/**
		 * {@inheritDoc}
		 */
		public String toString() {
			return TextUtil.get("type.annular");
		}
	}

	/**
	 * Rectangular helper.
	 *
	 * @author Denis_Murashev
	 */
	private final class RectangularForm implements Helper {

		/**
		 * Width index.
		 */
		private static final int WIDTH = 0;

		/**
		 * Height index.
		 */
		private static final int HEHGHT = 1;

		/**
		 * {@inheritDoc}
		 */
		public void update(AbstractSection section) {
			SectionForm.this.setEnabled(HEHGHT, true);
			String description = TextUtil.get("title.size");
			SectionForm.this.setDescription(WIDTH, description);
			SectionForm.this.setDescription(HEHGHT, description);
			if (section != null) {
				SectionForm.this.setValue(WIDTH,
						((RectangularSection) section).getBiggerSize());
				SectionForm.this.setValue(HEHGHT,
						((RectangularSection) section).getSmallerSize());
			}
		}

		/**
		 * {@inheritDoc}
		 */
		public AbstractSection getResult() {
			return new RectangularSection((LengthValue) getValue(WIDTH),
					(LengthValue) getValue(HEHGHT),
					(LengthValue) getValue(LENGTH),
					(PressureValue) getValue(YOUNG),
					(TorqueValue) getValue(TORQUE));
		}

		/**
		 * {@inheritDoc}
		 */
		public String toString() {
			return TextUtil.get("type.rectangular");
		}
	}
}
