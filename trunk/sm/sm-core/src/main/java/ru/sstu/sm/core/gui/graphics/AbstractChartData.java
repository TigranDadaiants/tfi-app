package ru.sstu.sm.core.gui.graphics;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.measure.quantity.Length;

import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;

import ru.sstu.sm.core.domain.AreaValue;
import ru.sstu.sm.core.domain.Direction;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>AbstractChartData</code> class describes data for chart.
 *
 * @author Denis_Murashev
 * @param <T> type of section
 * @since SM 2.0
 */
public abstract class AbstractChartData<T extends Section> {

	/**
	 * SM engine.
	 */
	private final AbstractEngine<T> engine;

	/**
	 * Markers.
	 */
	private final Collection<Marker> markers = new ArrayList<Marker>();

	/**
	 * Annotations.
	 */
	private final Collection<XYAnnotation> annotations
			= new ArrayList<XYAnnotation>();

	/**
	 * Initializes chart data.
	 *
	 * @param engine engine
	 */
	protected AbstractChartData(AbstractEngine<T> engine) {
		this.engine = engine;
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
	 * Provides data for plots.
	 *
	 * @return data for plots
	 */
	protected abstract PlotData[] getChartsData();

	/**
	 * Provides list of shapes for loads for given section.
	 *
	 * @param section section
	 * @param length  total length
	 * @param width   total width
	 * @return list of loads
	 */
	protected abstract List<Path2D> getLoads(T section, double length,
			double width);

	/**
	 * Provides direction.
	 *
	 * @return direction
	 */
	Direction getDirection() {
		return engine.getConfig().getDirection();
	}

	/**
	 * Provides axis title.
	 *
	 * @return axis title
	 */
	String getAxisTitle() {
		return null;
	}

	/**
	 * Provides plot data for task scheme.
	 *
	 * @return plot data for scheme
	 */
	PlotData getScheme() {
		return new PlotData(new SectionsDataset<T>(engine),
				TextUtil.get("entity.crosssection"), AreaValue.UNIT);
	}

	/**
	 * Provides sections markers.
	 *
	 * @return the markers
	 */
	Iterable<Marker> getMarkers() {
		return markers;
	}

	/**
	 * Provides annotations for loads.
	 *
	 * @return annotations
	 */
	Iterable<XYAnnotation> getAnnotations() {
		return annotations;
	}

	/**
	 * Provides borders between sections.
	 */
	void processSections() {
		markers.clear();
		annotations.clear();
		double length = 0.0;
		double width = 0.0;
		Marker marker = new ValueMarker(length);
		marker.setPaint(Color.RED);
		markers.add(marker);
		for (T section : engine.getConfig()) {
			length += section.getLength().doubleValue(Length.UNIT);
			if (section.getWidth() > width) {
				width = section.getWidth();
			}
			marker = new ValueMarker(length);
			marker.setPaint(Color.RED);
			markers.add(marker);
		}
		float x = 0.0f;
		for (T section : engine.getConfig()) {
			x += section.getLength().floatValue(Length.UNIT);
			for (Path2D path : getLoads(section, length, width)) {
				float[] matrix = {1, 0, 0, 1, x, 0};
				path.transform(new AffineTransform(matrix));
				annotations.add(new XYShapeAnnotation(path));
			}
		}
	}
}
