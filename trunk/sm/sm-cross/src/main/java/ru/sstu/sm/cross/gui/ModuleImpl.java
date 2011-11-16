package ru.sstu.sm.cross.gui;

import javax.swing.JPanel;

import ru.sstu.sm.core.gui.graphics.AbstractChartData;
import ru.sstu.sm.core.gui.graphics.GraphicsPanel;
import ru.sstu.sm.core.module.Module;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.cross.domain.SimpleSection;
import ru.sstu.sm.cross.service.TaskEngine;

/**
 * <code>ModuleImpl</code> class is {@link Module} implementation for
 * Cross Task.
 *
 * @author Denis_Murashev
 * @since SM 2.0
 */
public class ModuleImpl implements Module<SimpleSection> {

	/**
	 * Task engine.
	 */
	private TaskEngine engine = new TaskEngine();

	/**
	 * {@inheritDoc}
	 */
	public String getTitle() {
		return TextUtil.get("title.cross");
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
			extends GraphicsPanel<SimpleSection> {

		/**
		 * Serial version UID.
		 */
		private static final long serialVersionUID = -7230262326125313202L;

		/**
		 * @param engine engine
		 */
		private GraphicsPanelImpl(AbstractEngine<SimpleSection> engine) {
			super(engine);
		}

		/**
		 * {@inheritDoc}
		 */
		protected AbstractChartData<SimpleSection> createChartData(
				AbstractEngine<SimpleSection> engine) {
			return new ChartDataImpl((TaskEngine) engine);
		}
	}
}
