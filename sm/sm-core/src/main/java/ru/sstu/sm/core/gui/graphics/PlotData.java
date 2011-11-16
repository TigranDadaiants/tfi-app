package ru.sstu.sm.core.gui.graphics;

import javax.measure.unit.Unit;

import org.jfree.data.xy.XYDataset;

import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>PlotData</code> interface represents data for single plot.
 *
 * @author Denis_Murashev
 * @since SM 2.0
 */
public class PlotData {

	/**
	 * Data set.
	 */
	private XYDataset dataset;

	/**
	 * Axis title.
	 */
	private String axisTitle;

	/**
	 * Initializes plotting data.
	 *
	 * @param dataset dataset
	 * @param title   plot title
	 * @param unit    unit
	 */
	public PlotData(XYDataset dataset, String title, Unit<?> unit) {
		this.dataset = dataset;
		StringBuilder buffer = new StringBuilder(title);
		buffer.append(", ").append(TextUtil.get(unit));
		this.axisTitle = buffer.toString();
	}

	/**
	 * Provides dataset.
	 *
	 * @return dataset
	 */
	XYDataset getDataset() {
		return dataset;
	}

	/**
	 * Provides axis title.
	 *
	 * @return axis title
	 */
	String getAxisTitle() {
		return axisTitle;
	}
}
