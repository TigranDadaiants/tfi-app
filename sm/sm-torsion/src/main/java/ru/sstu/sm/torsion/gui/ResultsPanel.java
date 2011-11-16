package ru.sstu.sm.torsion.gui;

import ru.sstu.sm.core.gui.AbstractResultsPanel;
import ru.sstu.sm.torsion.domain.AbstractSection;
import ru.sstu.sm.torsion.service.TaskEngine;

/**
 * <code>ResultsPanel</code> class is panel for results.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
final class ResultsPanel extends AbstractResultsPanel<AbstractSection> {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 450534746686995866L;

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
			new Column("column.moment", AbstractSection.TORQUE),
			new Column("column.inertia", AbstractSection.INERTIA),
			new Column("column.minSize", AbstractSection.MINIMAL_SIZE),
			new Column("column.angle", AbstractSection.ANGLE),
			new Column("column.totalAngle", AbstractSection.TOTAL),
		};
	}
}
