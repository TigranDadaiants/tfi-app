package ru.sstu.spheres.gui;

import java.awt.Dimension;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import ru.sstu.spheres.core.Sphere;

/**
 * <code>HistogramPanel</code> class holds JFreeChart histogram.
 *
 * @author Denis_Murashev
 * @since Spheres 1.0
 */
public class HistogramPanel extends ChartPanel {

	private static final long serialVersionUID = 6947495394204057832L;

	private final int width;
	private final int height;

	public HistogramPanel(int width, int height) {
		super(null);
		this.width = width;
		this.height = height;
	}

	public void setSpheres(List<Sphere> spheres) {
		int size = spheres.size();
		if (size < 1) {
			return;
		}
		double[] data = new double[size];
		double min = Float.MAX_VALUE;
		double max = 0;
		int index = 0;
		for (Sphere s : spheres) {
			data[index] = s.getRadius();
			if (data[index] < min) {
				min = data[index];
			}
			if (data[index] > max) {
				max = data[index];
			}
			index++;
		}
		HistogramDataset dataset = new HistogramDataset();
		dataset.setType(HistogramType.FREQUENCY);
		int bins = (int) (max - min + 1);
		dataset.addSeries("???", data, bins);
		JFreeChart chart = ChartFactory.createHistogram(
				"Histogram Demo", 
				null, 
				null, 
				dataset, 
				PlotOrientation.VERTICAL, 
				true, 
				false, 
				false
		);
		setChart(chart);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}
