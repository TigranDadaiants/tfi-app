package ru.sstu.sm.core.gui.menu.standard;

import ru.sstu.sm.core.gui.menu.MenuAction;
import ru.sstu.sm.core.util.TextUtil;

/**
 * Class FileMenu.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public abstract class FileMenu implements MenuAction {

	/**
	 * @return parent's menu name
	 */
	public final String getParent() {
		return TextUtil.get("menu.file");
	}
}
