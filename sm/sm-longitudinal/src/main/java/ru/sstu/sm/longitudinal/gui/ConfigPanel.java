package ru.sstu.sm.longitudinal.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.measure.quantity.Length;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ru.sstu.sm.core.domain.Direction;
import ru.sstu.sm.core.domain.LengthValue;
import ru.sstu.sm.core.gui.AbstractSectionsPanel;
import ru.sstu.sm.core.gui.forms.AbstractForm;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.longitudinal.domain.SimpleSection;
import ru.sstu.sm.longitudinal.domain.TaskConfig;
import ru.sstu.sm.longitudinal.service.TaskEngine;

/**
 * <code>ConfigPanel</code> class is panel for configuration.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class ConfigPanel extends JPanel implements Observer {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -3713426042202397771L;

	/**
	 * Zero insets.
	 */
	private static final Insets INSETS = new Insets(0, 0, 0, 0);

	/**
	 * 5 pixels insets.
	 */
	private static final Insets INSETS_5 = new Insets(5, 5, 5, 5);

	/**
	 * Data field.
	 */
	private final JTextField deltaField = new JTextField(10);

	/**
	 * Task engine.
	 */
	private final TaskEngine engine;

	/**
	 * Initializes panel.
	 *
	 * @param engine task engine
	 */
	public ConfigPanel(TaskEngine engine) {
		super(new GridBagLayout());
		this.engine = engine;
		this.engine.addObserver(this);
		setName(TextUtil.get("panel.config"));
		generatePanel();
	}

	/**
	 * This method is called whenever the observed object is changed. An
	 * application calls an <tt>Observable</tt> object's
	 * <code>notifyObservers</code> method to have all the object's
	 * observers notified of the change.
	 *
	 * @param   o     the observable object.
	 * @param   arg   an argument passed to the <code>notifyObservers</code>
	 *                 method.
	 */
	public void update(Observable o, Object arg) {
		removeAll();
		generatePanel();
	}

	/**
	 * Generates panel.
	 */
	private void generatePanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.add(getTypePanel(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, INSETS,
				0, 0));
		JPanel valuesPanel = new SectionsPanel(TextUtil.get("panel.sections"));
		panel.add(valuesPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, INSETS,
				0, 0));
		add(panel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, INSETS,
				0, 0));
	}

	/**
	 * @return type panel
	 */
	private JPanel getTypePanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		final JCheckBox direction = new JCheckBox(TextUtil.get("direct.down"));
		direction.setSelected(engine.getConfig().getDirection()
				== Direction.DOWN);
		final int width = 3;
		int column = 0;
		panel.add(direction, new GridBagConstraints(column, 0, width, 1,
				1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				INSETS_5, 0, 0));
		direction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (direction.isSelected()) {
					engine.getConfig().setDirection(Direction.DOWN);
				} else {
					engine.getConfig().setDirection(Direction.UP);
				}
			}
		});

		final JCheckBox checkBox = new JCheckBox(TextUtil.get("type.hard"));
		checkBox.setSelected(engine.getConfig().getType()
				== TaskConfig.Type.HARD);
		panel.add(checkBox, new GridBagConstraints(column, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, INSETS_5,
				0, 0));
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deltaField.setEnabled(checkBox.isSelected());
				if (checkBox.isSelected()) {
					engine.getConfig().setType(TaskConfig.Type.HARD);
				} else {
					engine.getConfig().setType(TaskConfig.Type.FREE);
				}
			}
		});

		column++;
		panel.add(new JLabel(TextUtil.get("config.delta.m")),
				new GridBagConstraints(column, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST, GridBagConstraints.BOTH,
						INSETS_5, 0, 0));
		deltaField.setText(String.valueOf(((TaskConfig) engine.getConfig())
				.getDelta().doubleValue(Length.UNIT)));
		deltaField.setEnabled(checkBox.isSelected());
		column++;
		panel.add(deltaField, new GridBagConstraints(column, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, INSETS_5,
				0, 0));

		JButton button = new JButton(TextUtil.get("action.calculate"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		column++;
		panel.add(button, new GridBagConstraints(column, 0, 1, 2, 1.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.BOTH, INSETS_5,
				0, 0));
		return panel;
	}

	/**
	 * Calls calculations.
	 */
	private void calculate() {
		try {
			TaskConfig config = (TaskConfig) engine.getConfig();
			if (config.getType() == TaskConfig.Type.HARD) {
				double delta = Double.parseDouble(deltaField.getText());
				config.setDelta(new LengthValue(delta));
			}
			engine.calculate();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	/**
	 * Sections panel.
	 *
	 * @author Denis_Murashev
	 */
	private final class SectionsPanel
			extends AbstractSectionsPanel<SimpleSection> {

		/**
		 * Serial version UID.
		 */
		private static final long serialVersionUID = -5532648706364887899L;

		/**
		 * @param title panel title
		 */
		private SectionsPanel(String title) {
			super(title, engine);
		}

		/**
		 * Provides form for section properties editing.
		 *
		 * @param section old section
		 * @return form for section editing
		 */
		protected AbstractForm getSectionForm(SimpleSection section) {
			return new SectionForm(section);
		}
	}
}
