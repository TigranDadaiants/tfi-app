package ru.sstu.sm.core.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import ru.sstu.sm.core.gui.menu.MenuHelper;
import ru.sstu.sm.core.gui.menu.standard.ExitMenu;
import ru.sstu.sm.core.gui.menu.standard.LoadConfigMenu;
import ru.sstu.sm.core.gui.menu.standard.ModuleMenu;
import ru.sstu.sm.core.gui.menu.standard.NewConfigMenu;
import ru.sstu.sm.core.gui.menu.standard.SaveAsConfigMenu;
import ru.sstu.sm.core.module.Module;
import ru.sstu.sm.core.module.ModuleManager;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>MainFrame</code> class represents main application frame.
 * The class is responsible for creating window with menu and tabs.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class MainFrame extends JFrame {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -4576013148027426689L;

	/**
	 * Default width.
	 */
	private static final int WIDTH = 900;

	/**
	 * Default height.
	 */
	private static final int HEIGHT = 600;

	/**
	 * Tabbed pane.
	 */
	private JTabbedPane tabbedPane;

	/**
	 * Initializes main frame.
	 */
	public MainFrame() {
		createMenu();
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		update();
		pack();
		setPosition();
		setVisible(true);
	}

	/**
	 * Updates task configuration.
	 */
	public void update() {
		Module<?> module = ModuleManager.getInstance().getCurrent();
		String title = TextUtil.get("title.main") + ' ' + module.getTitle();
		setTitle(title);
		module.getEngine().calculate();
		createTabs();
	}

	/**
	 * Creates tabs.
	 */
	private void createTabs() {
		tabbedPane.removeAll();
		Module<?> module = ModuleManager.getInstance().getCurrent();
		for (JPanel panel : module.getTabPanels()) {
			tabbedPane.addTab(panel.getName(), panel);
		}
	}

	/**
	 * Creates menu.
	 */
	private void createMenu() {
		final JFileChooser chooser = new JFileChooser(".");
		chooser.setFileFilter(new FileNameExtensionFilter(TextUtil
				.get("config.file"), "xml", "cfg"));
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		MenuHelper.addMenuItem(menuBar, new NewConfigMenu());
		MenuHelper.addMenuItem(menuBar, new LoadConfigMenu(this, chooser));
		MenuHelper.addMenuItem(menuBar, new SaveAsConfigMenu(this, chooser));
		MenuHelper.addMenuItem(menuBar, new ExitMenu());
		for (Module<?> module : ModuleManager.getInstance().getModules()) {
			MenuHelper.addMenuItem(menuBar, new ModuleMenu(module, this));
		}
	}

	/**
	 * Centers frame position.
	 */
	private void setPosition() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getPreferredSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
	}

	/**
	 * Main function.
	 *
	 * @param args arguments
	 */
	public static void main(String[] args) {
		new MainFrame();
	}
}
