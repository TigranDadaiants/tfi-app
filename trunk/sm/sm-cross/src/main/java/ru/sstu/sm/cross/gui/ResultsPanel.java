package ru.sstu.sm.cross.gui;

import ru.sstu.sm.core.gui.AbstractResultsPanel;
import ru.sstu.sm.cross.domain.SimpleSection;
import ru.sstu.sm.cross.service.TaskEngine;

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
	private static final long serialVersionUID = 7028353542276732042L;

	/**
	 * Initializes panel.
	 *
	 * @param engine task engine
	 */
	public ResultsPanel(TaskEngine engine) {
		super(engine);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Column[] getColumns() {
		return new Column[]{
			new Column("column.load", SimpleSection.LOAD),
			new Column("column.moment", SimpleSection.TORQUE),
			new Column("column.angle", SimpleSection.ANGLE),
			new Column("column.deflection", SimpleSection.DEFLECTION),
		};
	}
}
