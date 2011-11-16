package ru.sstu.sm.torsion.gui;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import javax.measure.quantity.Angle;

import ru.sstu.sm.core.domain.TorqueValue;
import ru.sstu.sm.core.gui.graphics.AbstractChartData;
import ru.sstu.sm.core.gui.graphics.PlotData;
import ru.sstu.sm.core.gui.graphics.ResultDataset;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.sm.torsion.domain.AbstractSection;

/**
 * <code>ChartDataImpl</code> class is implementation of
 * {@link AbstractChartData} for Torsion Task.
 *
 * @author Denis Murashev
 * @since SM 2.0
 */
public class ChartDataImpl extends AbstractChartData<AbstractSection> {

	/**
	 * Initializes chart data.
	 *
	 * @param engine engine
	 */
	public ChartDataImpl(AbstractEngine<AbstractSection> engine) {
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
				new PlotData(new ResultDataset<AbstractSection>(getEngine(),
						AbstractSection.TORQUE),
						TextUtil.get("entity.moment"), TorqueValue.UNIT),
				new PlotData(new ResultDataset<AbstractSection>(getEngine(),
						AbstractSection.TOTAL),
						TextUtil.get("title.angle"), Angle.UNIT),
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
	protected List<Path2D> getLoads(AbstractSection section,
			double length, double width) {
		List<Path2D> paths = new ArrayList<Path2D>();
		TorqueValue load = section.getTorque();
		if (load == null || load.getSI() == 0.0) {
			return paths;
		}
		int sign = load.getSI() > 0.0 ? 1 : -1;
		final double big = 0.5;
		final double small = 0.1;
		Path2D path = new Path2D.Double();
		path.moveTo(0, 0);
		path.lineTo(0, 1);
		path.lineTo(sign, 1);
		path.lineTo(sign * big, 1.0 + small);
		path.lineTo(sign, 1);
		path.lineTo(sign * big, 1.0 - small);
		path.lineTo(sign, 1);
		path.lineTo(0, 1);
		path.lineTo(0, -1);
		path.lineTo(-sign, -1);
		path.lineTo(-sign * big, -1.0 - small);
		path.lineTo(-sign, -1);
		path.lineTo(-sign * big, -1.0 + small);
		path.lineTo(-sign, -1);
		path.lineTo(0, -1);
		path.closePath();
		final float x = (float) (0.02 * length);
		final float y = (float) (0.05 * width);
		path.transform(new AffineTransform(new float[]{x, 0, 0, y}));
		paths.add(path);
		return paths;
	}
}
