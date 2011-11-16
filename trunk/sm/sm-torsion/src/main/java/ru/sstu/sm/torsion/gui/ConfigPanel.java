package ru.sstu.sm.torsion.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ru.sstu.sm.core.domain.Direction;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.gui.AbstractSectionsPanel;
import ru.sstu.sm.core.gui.forms.AbstractForm;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.torsion.domain.AbstractSection;
import ru.sstu.sm.torsion.domain.TaskConfig;
import ru.sstu.sm.torsion.service.TaskEngine;

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
	private static final long serialVersionUID = -1855477861835635466L;

	/**
	 * Default width.
	 */
	private static final int WIDTH = 800;

	/**
	 * Default height.
	 */
	private static final int HEIGHT = 600;

	/**
	 * Zero insets.
	 */
	private static final Insets INSETS = new Insets(0, 0, 0, 0);

	/**
	 * 5 pixels insets.
	 */
	private static final Insets INSETS_5 = new Insets(5, 5, 5, 5);

	/**
	 * Task engine.
	 */
	private final TaskEngine engine;

	/**
	 * Text field for limit value.
	 */
	private final JTextField limitText = new JTextField(20);

	/**
	 * Initializes panel.
	 *
	 * @param engine SM engine
	 */
	public ConfigPanel(TaskEngine engine) {
		super(new GridBagLayout());
		this.engine = engine;
		this.engine.addObserver(this);
		setName(TextUtil.get("panel.config"));
		generatePanel();
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Observable o, Object arg) {
		try {
			double limit = Double.parseDouble(limitText.getText());
			((TaskConfig) engine.getConfig())
				.setLimit(new PressureValue(limit));
		} finally {
			removeAll();
			generatePanel();
		}
	}

	/**
	 * Generates panel.
	 */
	private void generatePanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.add(getTypePanel(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, INSETS,
				0, 0));
		JPanel valuesPanel = new SectionsPanel(engine);
		panel.add(valuesPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, INSETS,
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
		final JCheckBox direction = new JCheckBox(TextUtil.get("direct.left"));
		direction.setSelected(engine.getConfig().getDirection()
				== Direction.LEFT);
		final int gridWidth = 3;
		panel.add(direction, new GridBagConstraints(0, 0, gridWidth, 1,
				1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				INSETS_5, 0, 0));
		direction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (direction.isSelected()) {
					engine.getConfig().setDirection(Direction.LEFT);
				} else {
					engine.getConfig().setDirection(Direction.RIGHT);
				}
			}
		});

		final JCheckBox checkBox = new JCheckBox(TextUtil.get("type.hard"));
		checkBox.setSelected(engine.getConfig().getType()
				== TaskConfig.Type.HARD);
		int column = 0;
		panel.add(checkBox, new GridBagConstraints(column, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, INSETS_5,
				0, 0));
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBox.isSelected()) {
					engine.getConfig().setType(TaskConfig.Type.HARD);
				} else {
					engine.getConfig().setType(TaskConfig.Type.FREE);
				}
			}
		});
		column++;
		panel.add(new JLabel(TextUtil.get("title.load.limit")),
				new GridBagConstraints(column, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, INSETS_5,
				0, 0));
		column++;
		panel.add(limitText, new GridBagConstraints(column, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, INSETS_5,
				0, 0));
		limitText.setText(String.valueOf(((TaskConfig) engine.getConfig())
				.getLimit().doubleValue(PressureValue.UNIT)));
		JButton button = new JButton(TextUtil.get("action.calculate"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		column++;
		panel.add(button, new GridBagConstraints(column, 0, 1, 2, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, INSETS_5,
				0, 0));
		return panel;
	}

	/**
	 * Calls calculations.
	 */
	private void calculate() {
		try {
			double limit = Double.parseDouble(limitText.getText()
					.replace(',', '.'));
			((TaskConfig) engine.getConfig())
					.setLimit(new PressureValue(limit));
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
	private static final class SectionsPanel
			extends AbstractSectionsPanel<AbstractSection> {

		/**
		 * Serial version UID.
		 */
		private static final long serialVersionUID = 4074793415412851714L;

		/**
		 * Initializes sections panel.
		 *
		 * @param engine engine
		 */
		private SectionsPanel(TaskEngine engine) {
			super(TextUtil.get("panel.sections"), engine);
		}

		/**
		 * {@inheritDoc}
		 */
		protected AbstractForm getSectionForm(AbstractSection section) {
			return new SectionForm(section);
		}
	}
}
