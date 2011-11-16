package ru.sstu.sm.core.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.util.TextUtil;

/**
 * Class <code>AbstractListPanel</code> represents abstract panel for working
 * with configurations.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public abstract class AbstractListPanel extends JPanel {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 7568694066262942535L;

	/**
	 * 5 pixels insets.
	 */
	private static final Insets INSETS_5 = new Insets(5, 5, 5, 5);

	/**
	 * Selected index.
	 */
	private int selectedIndex = -1;

	/**
	 * @param title title
	 */
	protected AbstractListPanel(String title) {
		super(new GridBagLayout());
		createGUI(title);
	}

	/**
	 * Provides list of values.
	 *
	 * @return list of values
	 */
	protected abstract Config<?> getValues();

	/**
	 * Handler for adding new value.
	 */
	protected abstract void addValue();

	/**
	 * Handler for editing existing value.
	 *
	 * @param index index of value to be edited
	 */
	protected abstract void updateValue(int index);

	/**
	 * Handler for deleting value.
	 *
	 * @param index index of value to be deleted
	 */
	protected abstract void deleteValue(int index);

	/**
	 * Can the value with given index be edited.
	 *
	 * @param index index
	 * @return true if value can be edited
	 */
	protected abstract boolean canBeEdited(int index);

	/**
	 * Creating of GUI.
	 *
	 * @param title title
	 */
	private void createGUI(String title) {
		int row = 0;
		add(new JLabel(title), new GridBagConstraints(row, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, INSETS_5,
				0, 0));

		JButton button = new JButton(TextUtil.get("action.add"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addValue();
			}
		});
		row++;
		add(button, new GridBagConstraints(row, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, INSETS_5,
				0, 0));

		final JButton updateButton = new JButton(TextUtil.get("action.edit"));
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateValue(selectedIndex);
			}
		});
		row++;
		add(updateButton, new GridBagConstraints(row, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, INSETS_5,
				0, 0));

		final JButton deleteButton = new JButton(TextUtil.get("action.delete"));
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteValue(selectedIndex);
			}
		});
		row++;
		add(deleteButton, new GridBagConstraints(row, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, INSETS_5,
				0, 0));

		JTable table = new JTable(new ListTableModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					if (selectedIndex == -1
							|| selectedIndex == e.getFirstIndex()) {
						selectedIndex = e.getLastIndex();
					} else {
						selectedIndex = e.getFirstIndex();
					}
					updateButton.setEnabled(canBeEdited(selectedIndex));
					deleteButton.setEnabled(canBeEdited(selectedIndex));
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					updateValue(selectedIndex);
				}
			}
		});
		add(new JScrollPane(table), new GridBagConstraints(0, 1, 0, 0, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, INSETS_5,
				0, 0));
	}

	/**
	 * List table data model.
	 *
	 * @author Denis_Murashev
	 */
	private final class ListTableModel extends AbstractTableModel {

		/**
		 * Serial version UID.
		 */
		private static final long serialVersionUID = -3720318424994175945L;

		/**
		 * {@inheritDoc}
		 */
		public int getRowCount() {
			return getValues().size();
		}

		/**
		 * {@inheritDoc}
		 */
		public int getColumnCount() {
			return 1;
		}

		/**
		 * {@inheritDoc}
		 */
		public String getColumnName(int column) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		/**
		 * {@inheritDoc}
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			return getValues().get(rowIndex);
		}
	}
}
