package ru.sstu.exam.core;

import java.util.List;

/**
 * <code>TestPaper</code> class represents test paper with index and list of
 * questions (or items).
 *
 * @author Denis_Murashev
 * @since Exam 1.0
 */
public class TestPaper {

	private int index;
	private List<String> items;

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the items
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<String> items) {
		this.items = items;
	}
}
