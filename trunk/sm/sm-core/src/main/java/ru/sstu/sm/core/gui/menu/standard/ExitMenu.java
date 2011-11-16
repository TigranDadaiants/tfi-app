package ru.sstu.sm.core.gui.menu.standard;

import ru.sstu.sm.core.util.TextUtil;

/**
 * Class <code>ExitMenu</code> is menu action implementation for Exit menu.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class ExitMenu extends FileMenu {

	/**
	 * @return actions name
	 */
	public String getName() {
		return TextUtil.get("action.exit");
	}

	/**
	 * Executes menu action.
	 */
	public void execute() {
		System.exit(0);
	}
}
