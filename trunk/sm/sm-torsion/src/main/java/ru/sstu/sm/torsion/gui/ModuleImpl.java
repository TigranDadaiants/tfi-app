package ru.sstu.sm.torsion.gui;

import javax.swing.JPanel;

import ru.sstu.sm.core.gui.graphics.AbstractChartData;
import ru.sstu.sm.core.gui.graphics.GraphicsPanel;
import ru.sstu.sm.core.module.Module;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.torsion.domain.AbstractSection;
import ru.sstu.sm.torsion.service.TaskEngine;

/**
 * <code>ModuleImpl</code> class is {@link Module} implementation for
 * Torsion Task.
 *
 * @author Denis_Murashev
 * @since SM 2.0
 */
public class ModuleImpl implements Module<AbstractSection> {

	/**
	 * Task engine.
	 */
	private TaskEngine engine = new TaskEngine();

	/**
	 * {@inheritDoc}
	 */
	public String getTitle() {
		return TextUtil.get("title.torsion");
	}

	/**
	 * {@inheritDoc}
	 */
	public TaskEngine getEngine() {
		return engine;
	}

	/**
	 * {@inheritDoc}
	 */
	public JPanel[] getTabPanels() {
		return new JPanel[] {
			new ConfigPanel(engine),
			new ResultsPanel(engine),
			new GraphicsPanelImpl(engine),
		};
	}

	/**
	 * Concrete {@link GraphicsPanel}.
	 *
	 * @author Denis_Murashev
	 */
	private static final class GraphicsPanelImpl
			extends GraphicsPanel<AbstractSection> {

		/**
		 * Serial version UID.
		 */
		private static final long serialVersionUID = 3080997645847097164L;

		/**
		 * @param engine engine
		 */
		private GraphicsPanelImpl(AbstractEngine<AbstractSection> engine) {
			super(engine);
		}

		/**
		 * {@inheritDoc}
		 */
		protected AbstractChartData<AbstractSection> createChartData(
				AbstractEngine<AbstractSection> engine) {
			return new ChartDataImpl(engine);
		}
	}
}
