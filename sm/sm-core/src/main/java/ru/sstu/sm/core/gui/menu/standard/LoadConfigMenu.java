package ru.sstu.sm.core.gui.menu.standard;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ru.sstu.sm.core.gui.MainFrame;
import ru.sstu.sm.core.service.ConfigManager;
import ru.sstu.sm.core.service.SMException;
import ru.sstu.sm.core.util.TextUtil;

/**
 * Class LoadConfigMenu.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class LoadConfigMenu extends FileMenu {

	/**
	 * Parent frame.
	 */
	private final MainFrame parent;

	/**
	 * File chooser.
	 */
	private final JFileChooser chooser;

	/**
	 * @param parent parent frame
	 * @param chooser file chooser
	 */
	public LoadConfigMenu(MainFrame parent, JFileChooser chooser) {
		this.parent = parent;
		this.chooser = chooser;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return TextUtil.get("action.open");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
			try {
				File file = chooser.getSelectedFile();
				ConfigManager.updateModule(file);
				parent.update();
			} catch (SMException e) {
				JOptionPane.showMessageDialog(parent, e.getMessage());
			}
		}
		parent.repaint();
	}
}
