package ru.sstu.sm.longitudinal.gui;

import ru.sstu.sm.core.gui.AbstractResultsPanel;
import ru.sstu.sm.longitudinal.domain.SimpleSection;
import ru.sstu.sm.longitudinal.service.TaskEngine;

/**
 * <code>ResultsPanel</code> class is panel for results.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
final class ResultsPanel extends AbstractResultsPanel<SimpleSection> {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 2357286133004219697L;

	/**
	 * Initializes panel.
	 *
	 * @param engine task engine
	 */
	ResultsPanel(TaskEngine engine) {
		super(engine);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Column[] getColumns() {
		return new Column[]{
			new Column("column.load", SimpleSection.LOAD),
			new Column("column.tension", SimpleSection.TENSION),
			new Column("column.extension", SimpleSection.EXTENSION),
			new Column("column.totalExtension", SimpleSection.TOTAL),
		};
	}
}
