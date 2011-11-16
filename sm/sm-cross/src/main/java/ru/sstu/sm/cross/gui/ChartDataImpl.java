package ru.sstu.sm.cross.gui;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import javax.measure.quantity.Angle;

import ru.sstu.sm.core.domain.ForceDensityValue;
import ru.sstu.sm.core.domain.ForceValue;
import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.core.gui.graphics.AbstractChartData;
import ru.sstu.sm.core.gui.graphics.PlotData;
import ru.sstu.sm.core.gui.graphics.ResultDataset;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.cross.domain.SimpleSection;
import ru.sstu.sm.cross.service.TaskEngine;

/**
 * <code>ChartDataImpl</code> class is implementation of
 * {@link AbstractChartData} for Cross Task.
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
						SimpleSection.TORQUE),
						TextUtil.get("entity.moment"), TorqueValue.UNIT),
				new PlotData(new ResultDataset<SimpleSection>(getEngine(),
						SimpleSection.ANGLE),
						TextUtil.get("title.angle"), Angle.UNIT),
				new PlotData(new ResultDataset<SimpleSection>(getEngine(),
						SimpleSection.DEFLECTION),
						TextUtil.get("title.deflection"), Angle.UNIT),
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
	protected List<Path2D> getLoads(SimpleSection section,
			double length, double width) {
		List<Path2D> paths = new ArrayList<Path2D>();
		ForceValue load = section.getLoad();
		if (load != null && load.getSI() != 0.0) {
			int sign = load.getSI() > 0.0 ? 1 : -1;
			Path2D.Double path = getLoadPath(length, width, sign);
			paths.add(path);
		}
		TorqueValue torque = section.getTorque();
		if (torque != null && torque.getSI() != 0.0) {
			int sign = torque.getSI() > 0.0 ? 1 : -1;
			Path2D.Double path = getMomentPath(length, width, sign);
			paths.add(path);
		}
		ForceDensityValue value = section.getDistributedLoad();
		if (value != null && value.getSI() != 0.0) {
			int sign = value.getSI() > 0.0 ? -1 : 1;
			double realLength = section.getLength().getSI();
			Path2D.Double path = getDistributedLoadPath(length, width, sign,
					realLength);
			paths.add(path);
		}
		return paths;
	}

	/**
	 * @param length length
	 * @param width  width
	 * @param sign   sign
	 * @return path for load
	 */
	private Path2D.Double getLoadPath(double length, double width, int sign) {
		Path2D.Double path = new Path2D.Double();
		final double horizontalOffset = 0.1;
		final double longOffset = 18;
		final double shortOffset = 12;
		path.moveTo(0, 0);
		path.lineTo(0, sign * longOffset);
		path.lineTo(horizontalOffset, sign * shortOffset);
		path.lineTo(0, sign * longOffset);
		path.lineTo(-horizontalOffset, sign * shortOffset);
		path.lineTo(0, sign * longOffset);
		path.closePath();
		final double factor = 0.05;
		float x = (float) (factor * length);
		float y = (float) (factor * width);
		path.transform(new AffineTransform(new float[]{x, 0, 0, y}));
		return path;
	}

	/**
	 * @param length length
	 * @param width  width
	 * @param sign   sign
	 * @return path for moment
	 */
	private Path2D.Double getMomentPath(double length, double width, int sign) {
		Path2D.Double path = new Path2D.Double();
		final double majorOffset = 0.5;
		final double minorOffset = 0.25;
		final double verticalOffset = 10;
		path.moveTo(0, 0);
		path.lineTo(0, verticalOffset);
		path.lineTo(-sign * majorOffset, verticalOffset);
		path.lineTo(-sign * minorOffset, verticalOffset + 1);
		path.lineTo(-sign * majorOffset, verticalOffset);
		path.lineTo(-sign * minorOffset, verticalOffset - 1);
		path.lineTo(-sign * majorOffset, verticalOffset);
		path.lineTo(0, verticalOffset);
		path.lineTo(0, -verticalOffset);
		path.lineTo(sign * majorOffset, -verticalOffset);
		path.lineTo(sign * minorOffset, -verticalOffset - 1);
		path.lineTo(sign * majorOffset, -verticalOffset);
		path.lineTo(sign * minorOffset, -verticalOffset + 1);
		path.lineTo(sign * majorOffset, -verticalOffset);
		path.lineTo(0, -verticalOffset);
		path.closePath();
		final double factor = 0.05;
		float x = (float) (factor * length);
		float y = (float) (factor * width);
		path.transform(new AffineTransform(new float[]{x, 0, 0, y}));
		return path;
	}

	/**
	 * @param length     length
	 * @param width      width
	 * @param sign       sign
	 * @param realLength real length
	 * @return path for distributed load
	 */
	private Path2D.Double getDistributedLoadPath(double length, double width,
			int sign, double realLength) {
		Path2D.Double path = new Path2D.Double();
		final double arrowFactor = 0.002;
		double arrow = arrowFactor * length;
		final double verticalOffset = 0.5;
		path.moveTo(0, 0);
		path.lineTo(arrow, sign * verticalOffset);
		path.lineTo(0, 0);
		path.lineTo(-arrow, sign * verticalOffset);
		path.lineTo(0, 0);
		path.lineTo(0, sign);
		final int maxArrows = 18;
		int n = (int) (1 + maxArrows * realLength / length);
		for (int i = 1; i <= n; i++) {
			double x = -realLength * i / n;
			path.lineTo(x, sign);
			path.moveTo(x, 0);
			path.lineTo(x + arrow, sign * verticalOffset);
			path.lineTo(x, 0);
			path.lineTo(x - arrow, sign * verticalOffset);
			path.lineTo(x, 0);
			path.lineTo(x, sign);
		}
		final double factor = 0.3;
		float x = 1.0f;
		float y = (float) (factor * width);
		path.transform(new AffineTransform(new float[]{x, 0, 0, y}));
		return path;
	}
}
