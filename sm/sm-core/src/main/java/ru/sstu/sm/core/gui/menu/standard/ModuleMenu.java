package ru.sstu.sm.core.gui.menu.standard;

import ru.sstu.sm.core.gui.MainFrame;
import ru.sstu.sm.core.gui.menu.MenuAction;
import ru.sstu.sm.core.module.Module;
import ru.sstu.sm.core.module.ModuleManager;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>ModulesMenu</code> class represents menu item for module selection.
 *
 * @author Denis_Murashev
 * @since SM 2.0
 */
public class ModuleMenu implements MenuAction {

	/**
	 * Current module.
	 */
	private final Module<?> module;

	/**
	 * Parent frame.
	 */
	private final MainFrame frame;

	/**
	 * @param module module instance
	 * @param frame  parent frame
	 */
	public ModuleMenu(Module<?> module, MainFrame frame) {
		this.module = module;
		this.frame = frame;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getParent() {
		return TextUtil.get("menu.modules");
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return module.getTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		ModuleManager.getInstance().setCurrent(module);
		frame.update();
	}
}
