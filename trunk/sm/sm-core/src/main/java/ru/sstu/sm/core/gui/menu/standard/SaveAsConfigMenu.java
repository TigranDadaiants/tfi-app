package ru.sstu.sm.core.gui.menu.standard;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ru.sstu.sm.core.module.ModuleManager;
import ru.sstu.sm.core.service.AbstractEngine;
import ru.sstu.sm.core.service.SMException;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>SaveAsConfigMenu</code> class is menu item for "Save As..." action.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class SaveAsConfigMenu extends FileMenu {

	/**
	 * Parent frame.
	 */
	private final JFrame parent;

	/**
	 * File chooser.
	 */
	private final JFileChooser chooser;

	/**
	 * @param parent  parent frame
	 * @param chooser file chooser
	 */
	public SaveAsConfigMenu(JFrame parent, JFileChooser chooser) {
		this.parent = parent;
		this.chooser = chooser;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return TextUtil.get("action.saveAs");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		AbstractEngine<?> engine = ModuleManager.getInstance().getCurrent()
				.getEngine();
		if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
			try {
				engine.saveConfig(chooser.getSelectedFile());
			} catch (SMException e) {
				JOptionPane.showMessageDialog(parent, e.getMessage());
			}
		}
		parent.repaint();
	}
}
