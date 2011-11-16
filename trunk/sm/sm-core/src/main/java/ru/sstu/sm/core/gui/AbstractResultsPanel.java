package ru.sstu.sm.core.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.measure.converter.UnitConverter;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.log4j.Logger;

import ru.sstu.math.util.PolynomialUtil;
import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>AbstractResultsPanel</code> class is abstract panel for results table.
 *
 * @author Denis A. Murashev
 * @param <T> type of section
 * @since SM 2.0
 */
public abstract class AbstractResultsPanel<T extends Section> extends JPanel
		implements Observer {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 8224818366204627254L;

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(AbstractResultsPanel.class);

	/**
	 * SM engine.
	 */
	private final AbstractEngine<T> engine;

	/**
	 * Table data model.
	 */
	private final LocalTableModel tableModel;

	/**
	 * Initializes panel.
	 *
	 * @param engine task engine
	 */
	protected AbstractResultsPanel(AbstractEngine<T> engine) {
		super(new GridBagLayout());
		this.engine = engine;
		this.engine.addObserver(this);
		this.tableModel = new LocalTableModel();
		setName(TextUtil.get("panel.result"));
		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
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
		log.debug("Results Panel is being updated");
		tableModel.fireTableDataChanged();
		repaint();
	}

	/**
	 * Provides engine.
	 *
	 * @return engine
	 */
	protected AbstractEngine<T> getEngine() {
		return engine;
	}

	/**
	 * Provides columns model.
	 *
	 * @return columns
	 * @since SM 3.0
	 */
	protected abstract Column[] getColumns();

	/**
	 * Converts polynomial function using given converter.
	 *
	 * @param polynom   polynomial function
	 * @param converter converter
	 * @return corrected function
	 * @since SM 3.0
	 */
	private PolynomialFunction convert(PolynomialFunction polynom,
			UnitConverter converter) {
		return PolynomialUtil.multiply(polynom, converter.convert(1.0));
	}

	/**
	 * Column model data.
	 *
	 * @author Denis_Murashev
	 * @since SM 3.0
	 */
	protected static final class Column {

		/**
		 * Column title.
		 */
		private final String title;

		/**
		 * Data index.
		 */
		private final int index;

		/**
		 * @param key   title key
		 * @param index index
		 */
		public Column(String key, int index) {
			this.title = TextUtil.get(key);
			this.index = index;
		}

		/**
		 * @return the title
		 */
		String getTitle() {
			return title;
		}

		/**
		 * @return the index
		 */
		int getIndex() {
			return index;
		}
	}

	/**
	 * Local table data model.
	 *
	 * @author Denis_Murashev
	 */
	private class LocalTableModel extends AbstractTableModel {

		/**
		 * Serial version UID.
		 */
		private static final long serialVersionUID = 5138444832271257386L;

		/**
		 * {@inheritDoc}
		 */
		public int getRowCount() {
			return engine.getConfig().size();
		}

		/**
		 * {@inheritDoc}
		 */
		public int getColumnCount() {
			return getColumns().length;
		}

		/**
		 * {@inheritDoc}
		 */
		public String getColumnName(int column) {
			return getColumns()[column].getTitle();
		}

		/**
		 * {@inheritDoc}
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Config<T> config = getEngine().getConfig();
			T section = config.get(rowIndex);
			Column column = getColumns()[columnIndex];
			PolynomialFunction polynom = convert(section.get(column.getIndex()),
					config.getConverter(column.getIndex()));
			StringBuilder buffer = new StringBuilder();
			buffer.append(polynom);
			if (polynom.degree() > 0) {
				double position = section.getLength().getSI();
				buffer.append(" (");
				buffer.append(polynom.value(0.0));
				buffer.append(" --- ");
				buffer.append(polynom.value(position));
				buffer.append(')');
			}
			return buffer.toString();
		}
	}
}
