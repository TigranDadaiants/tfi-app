package ru.sstu.sm.core.gui.graphics;

import java.util.ArrayList;
import java.util.List;

import javax.measure.quantity.Length;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;

import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;

/**
 * <code>ResultDataset</code> code represents data set for displaying of result.
 *
 * @author Denis Murashev
 * @param <T> type of section
 * @since SM 2.0
 */
public class ResultDataset<T extends Section> implements XYDataset {

	/**
	 * Points per section.
	 */
	private static final int POINTS_PER_SECTION = 105;

	/**
	 * Configuration.
	 */
	private Config<T> config;

	/**
	 * Sections lengths.
	 */
	private List<Double> lengths = new ArrayList<Double>();

	/**
	 * Sections widths.
	 */
	private List<Double> widths = new ArrayList<Double>();

	/**
	 * @param engine     engine
	 * @param resultType result type
	 */
	public ResultDataset(AbstractEngine<T> engine, int resultType) {
		config = engine.getConfig();
		double total = 0.0;
		lengths.add(0.0);
		widths.add(0.0);
		for (Section section : config) {
			double length = section.getLength().doubleValue(Length.UNIT);
			for (int i = 0; i < POINTS_PER_SECTION; i++) {
				double x = i * length / (POINTS_PER_SECTION - 1);
				double width = config.getConverter(resultType)
						.convert(section.getValue(resultType, x));
				lengths.add(total + x);
				widths.add(width);
			}
			total += length;
		}
		lengths.add(total);
		widths.add(0.0);
	}

	/**
	 * {@inheritDoc}
	 */
	public DomainOrder getDomainOrder() {
		return DomainOrder.NONE;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getItemCount(int series) {
		return config.size() * POINTS_PER_SECTION + 2;
	}

	/**
	 * {@inheritDoc}
	 */
	public Number getX(int series, int item) {
		return lengths.get(item);
	}

	/**
	 * {@inheritDoc}
	 */
	public double getXValue(int series, int item) {
		return lengths.get(item);
	}

	/**
	 * {@inheritDoc}
	 */
	public Number getY(int series, int item) {
		return widths.get(item);
	}

	/**
	 * {@inheritDoc}
	 */
	public double getYValue(int series, int item) {
		return widths.get(item);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSeriesCount() {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public Comparable<?> getSeriesKey(int series) {
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	public int indexOf(Comparable arg0) {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addChangeListener(DatasetChangeListener arg0) {
	}

	/**
	 * {@inheritDoc}
	 */
	public DatasetGroup getGroup() {
		return new DatasetGroup();
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeChangeListener(DatasetChangeListener arg0) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void setGroup(DatasetGroup arg0) {
	}
}
