package ru.sstu.sm.core.gui.menu;

/**
 * Interface <code>MenuAction</code> represents menu action.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public interface MenuAction {

	/**
	 * @return parent's menu name
	 */
	String getParent();

	/**
	 * @return actions name
	 */
	String getName();

	/**
	 * Executes menu action.
	 */
	void execute();
}
