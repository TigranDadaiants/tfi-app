package ru.sstu.sm.core.gui.graphics;

import java.util.ArrayList;
import java.util.List;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;

import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;

/**
 * <code>SectionsDataset</code> class represents data set for displaying of
 * configuration.
 *
 * @author Denis Murashev
 * @param <T> type of section
 * @since SM 2.0
 */
public class SectionsDataset<T extends Section> implements XYDataset {

	/**
	 * Configuration.
	 */
	private Config<T> config;

	/**
	 * Lengths.
	 */
	private List<Double> lengths = new ArrayList<Double>();

	/**
	 * Widths.
	 */
	private List<Double> widths = new ArrayList<Double>();

	/**
	 * Initializes dataset.
	 *
	 * @param engine engine
	 */
	public SectionsDataset(AbstractEngine<T> engine) {
		config = engine.getConfig();
		double length = 0;
		widths.add(0.0);
		for (Section s : config) {
			lengths.add(length);
			lengths.add(length);
			length += s.getLength().getSI();
			widths.add(s.getWidth());
			widths.add(s.getWidth());
		}
		widths.add(0.0);
		lengths.add(length);
		lengths.add(length);
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
		return (config.size() << 1) + 2;
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
		return (series == 1 ? 1 : -1) * widths.get(item);
	}

	/**
	 * {@inheritDoc}
	 */
	public double getYValue(int series, int item) {
		return (series == 1 ? 1 : -1) * widths.get(item);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSeriesCount() {
		return 2;
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
