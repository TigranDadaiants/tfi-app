package ru.sstu.sm.longitudinal.gui;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import javax.measure.quantity.Force;
import javax.measure.unit.SI;

import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.PressureValue;
import ru.sstu.sm.core.domain.Value;
import ru.sstu.sm.core.gui.graphics.AbstractChartData;
import ru.sstu.sm.core.gui.graphics.PlotData;
import ru.sstu.sm.core.gui.graphics.ResultDataset;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.longitudinal.domain.SimpleSection;
import ru.sstu.sm.longitudinal.service.TaskEngine;

/**
 * <code>ChartDataImpl</code> class is implementation of
 * {@link AbstractChartData} for Longitudinal Task.
 *
 * @author Denis Murashev
 * @since SM 2.0
 */
public class ChartDataImpl extends AbstractChartData<SimpleSection> {

	/**
	 * Initializes chart data.
	 *
	 * @param engine engine
	 */
	public ChartDataImpl(TaskEngine engine) {
		super(engine);
	}

	/**
	 * Provides data for plots.
	 *
	 * @return data for plots
	 */
	@Override
	protected PlotData[] getChartsData() {
		return new PlotData[]{
				new PlotData(new ResultDataset<SimpleSection>(getEngine(),
						SimpleSection.LOAD),
						TextUtil.get("title.load"), ForceValue.UNIT),
				new PlotData(new ResultDataset<SimpleSection>(getEngine(),
						SimpleSection.TENSION),
						TextUtil.get("title.tension"), PressureValue.UNIT),
				new PlotData(new ResultDataset<SimpleSection>(getEngine(),
						SimpleSection.TOTAL),
						TextUtil.get("title.extension"), SI.MILLIMETER),
		};
	}

	/**
	 * Provides list of shapes for loads for given section.
	 *
	 * @param section section
	 * @param length  total length
	 * @param width   total width
	 * @return list of loads
	 */
	@Override
	protected List<Path2D> getLoads(SimpleSection section, double length,
			double width) {
		final double longMove = 0.5;
		final double shortMove = 0.05;
		List<Path2D> paths = new ArrayList<Path2D>();
		Value<Force> load = section.getLoad();
		if (load == null || load.getSI() == 0.0) {
			return paths;
		}
		int sign = load.getSI() > 0.0 ? 1 : -1;
		Path2D path = new Path2D.Double();
		path.moveTo(0, 0);
		path.lineTo(sign, 0);
		path.lineTo(sign * longMove, longMove);
		path.lineTo(sign, 0);
		path.lineTo(sign * longMove, -longMove);
		path.lineTo(sign, 0);
		path.closePath();
		float x = (float) (shortMove * length);
		float y = (float) (shortMove * width);
		path.transform(new AffineTransform(new float[]{x, 0, 0, y}));
		paths.add(path);
		return paths;
	}
}
