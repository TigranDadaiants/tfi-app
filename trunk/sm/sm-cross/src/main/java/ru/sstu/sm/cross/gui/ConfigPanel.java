package ru.sstu.sm.cross.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ru.sstu.sm.core.domain.Direction;
import ru.sstu.sm.core.gui.AbstractSectionsPanel;
import ru.sstu.sm.core.gui.forms.AbstractForm;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.cross.domain.SimpleSection;
import ru.sstu.sm.cross.service.TaskEngine;

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
	private static final long serialVersionUID = 2762861276890573834L;

	/**
	 * Zero insets.
	 */
	private static final Insets INSETS = new Insets(0, 0, 0, 0);

	/**
	 * % pixels insets.
	 */
	private static final Insets INSETS_5 = new Insets(5, 5, 5, 5);

	/**
	 * Task engine.
	 */
	private final TaskEngine engine;

	/**
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
	 * This method is called whenever the observed object is changed. An
	 * application calls an <tt>Observable</tt> object's
	 * <code>notifyObservers</code> method to have all the object's
	 * observers notified of the change.
	 *
	 * @param o   the observable object.
	 * @param arg an argument passed to the <code>notifyObservers</code>
	 *            method.
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
		panel.add(getTypePanel(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, INSETS,
				0, 0));
		JPanel valuesPanel = new SectionsPanel(engine);
		panel.add(new JScrollPane(valuesPanel), new GridBagConstraints(0, 1,
				1, 1, 1.0, 1.0, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH, INSETS, 0, 0));

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
		panel.add(direction, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, INSETS_5,
				0, 0));
		direction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (direction.isSelected()) {
					engine.getConfig().setDirection(Direction.LEFT);
				} else {
					engine.getConfig().setDirection(Direction.RIGHT);
				}
			}
		});
		JButton button = new JButton(TextUtil.get("action.calculate"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		panel.add(button, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, INSETS_5,
				0, 0));

		return panel;
	}

	/**
	 * Calls calculations.
	 */
	private void calculate() {
		engine.calculate();
	}

	/**
	 * Sections panel.
	 *
	 * @author Denis_Murashev
	 */
	private static final class SectionsPanel
			extends AbstractSectionsPanel<SimpleSection> {

		/**
		 * Serial version UID.
		 */
		private static final long serialVersionUID = 3947121281393065751L;

		/**
		 * @param engine engine
		 */
		private SectionsPanel(TaskEngine engine) {
			super(TextUtil.get("panel.sections"), engine);
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
