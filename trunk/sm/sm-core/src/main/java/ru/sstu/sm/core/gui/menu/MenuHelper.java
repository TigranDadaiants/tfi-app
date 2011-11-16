package ru.sstu.sm.core.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Class <code>MenuHelper</code> helps to work with menu.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class MenuHelper {

	/**
	 * No instances needed.
	 */
	private MenuHelper() {
	}

	/**
	 * Adding of new menu item.
	 *
	 * @param menuBar menu bar
	 * @param action  menu action
	 */
	public static void addMenuItem(JMenuBar menuBar, final MenuAction action) {
		JMenu menu = findParent(menuBar, action.getParent());
		if (menu == null) {
			menu = new JMenu(action.getParent());
			menuBar.add(menu);
		}

		JMenuItem menuItem = new JMenuItem(action.getName());
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.execute();
			}
		});
		menu.add(menuItem);
	}

	/**
	 * Finds parent.
	 *
	 * @param menuBar menu bar
	 * @param name    menu name
	 * @return menu
	 */
	private static JMenu findParent(JMenuBar menuBar, String name) {
		int menuCount = menuBar.getMenuCount();
		for (int i = 0; i < menuCount; i++) {
			JMenu menu = menuBar.getMenu(i);
			if (menu.getText().equals(name)) {
				return menuBar.getMenu(i);
			}
		}
		return null;
	}
}
