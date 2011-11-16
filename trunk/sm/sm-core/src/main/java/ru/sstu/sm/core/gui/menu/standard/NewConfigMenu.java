package ru.sstu.sm.core.gui.menu.standard;

import ru.sstu.sm.core.module.ModuleManager;
import ru.sstu.sm.core.util.TextUtil;

/**
 * Class NewConfigMenu.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class NewConfigMenu extends FileMenu {

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return TextUtil.get("action.create");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		ModuleManager.getInstance().getCurrent().getEngine().clearConfig();
	}
}
