package ru.sstu.sm.core.module;

import javax.swing.JPanel;

import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.AbstractEngine;

/**
 * <code>Module</code> interface represents module for concrete task solution.
 *
 * @author Denis_Murashev
 * @param <T> type of section
 * @since SM 2.0
 */
public interface Module<T extends Section> {

	/**
	 * @return module title
	 */
	String getTitle();

	/**
	 * @return task solution engine
	 */
	AbstractEngine<T> getEngine();

	/**
	 * @return array of display panels
	 */
	JPanel[] getTabPanels();
}
