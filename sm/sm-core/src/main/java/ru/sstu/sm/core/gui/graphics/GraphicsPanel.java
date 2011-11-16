package ru.sstu.sm.core.gui.graphics;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;

import ru.sstu.sm.core.domain.Direction;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>GraphicsPanel</code> class represents panel for output of graphical
 * data.
 *
 * @author Denis Murashev
 * @param <T> type of section
 * @since SM 2.0
 */
public abstract class GraphicsPanel<T extends Section> extends ChartPanel
		implements Observer {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 7188153696738549839L;

	/**
	 * Gap.
	 */
	private static final double GAP = 25.0;

	/**
	 * Initializes graphical panel.
	 *
	 * @param engine engine
	 */
	protected GraphicsPanel(AbstractEngine<T> engine) {
		super(null, true, true, true, false, true);
		setChart(createCombinedChart(createChartData(engine)));
		setName(TextUtil.get("panel.graph"));
		engine.addObserver(this);
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
	@SuppressWarnings("unchecked")
	public void update(Observable o, Object arg) {
		AbstractEngine<T> engine = (AbstractEngine<T>) arg;
		setChart(createCombinedChart(createChartData(engine)));
	}

	/**
	 * Creates chart data for given engine.
	 *
	 * @param engine engine
	 * @return chart data
	 */
	protected abstract AbstractChartData<T> createChartData(
			AbstractEngine<T> engine);

	/**
	 * Creates combined chart for given data.
	 *
	 * @param data data
	 * @return chart
	 */
	private JFreeChart createCombinedChart(AbstractChartData<T> data) {
		final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(
				new NumberAxis(data.getAxisTitle()));
		plot.setGap(GAP);
		XYItemRenderer renderer = new StandardXYItemRenderer();
		renderer.setSeriesPaint(0, Color.DARK_GRAY);
		renderer.setSeriesPaint(1, Color.DARK_GRAY);
		NumberAxis rangeAxis = new NumberAxis(data.getScheme().getAxisTitle());
		XYPlot subplot = new XYPlot(data.getScheme().getDataset(), null,
				rangeAxis, renderer);
		subplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		data.processSections();
		for (Marker marker : data.getMarkers()) {
			subplot.addDomainMarker(marker);
		}
		for (XYAnnotation annotation : data.getAnnotations()) {
			subplot.addAnnotation(annotation);
		}
		plot.add(subplot, 1);
		renderer = new XYAreaRenderer();
		renderer.setSeriesPaint(0, Color.DARK_GRAY);
		for (PlotData plotData : data.getChartsData()) {
			rangeAxis = new NumberAxis(plotData.getAxisTitle());
			subplot = new XYPlot(plotData.getDataset(), null, rangeAxis,
					renderer);
			subplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
			for (Marker marker : data.getMarkers()) {
				subplot.addDomainMarker(marker);
			}
			subplot.addRangeMarker(new ValueMarker(0.0));
			plot.add(subplot, 1);
		}
		Direction direction = data.getDirection();
		if (direction == Direction.UP || direction == Direction.DOWN) {
			plot.setOrientation(PlotOrientation.HORIZONTAL);
		} else {
			plot.setOrientation(PlotOrientation.VERTICAL);
		}
		if (direction == Direction.DOWN || direction == Direction.LEFT) {
			plot.getDomainAxis(0).setInverted(true);
		}
		return new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT, plot, false);
	}
}
