package ru.sstu.sm.core.gui.dialogs;

import java.awt.Container;

import javax.swing.JOptionPane;

import ru.sstu.sm.core.gui.forms.AbstractForm;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>DialogHelper</code> class helps to operate with modal dialogs.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class DialogHelper {

	/**
	 * No instances needed.
	 */
	private DialogHelper() {
	}

	/**
	 * Shows modal dialog.
	 *
	 * @param parent parent frame
	 * @param form   data form
	 * @return data from dialog
	 * @param <T> type of section
	 */
	@SuppressWarnings("unchecked")
	public static <T> T executeDialog(Container parent, AbstractForm form) {
		do {
			if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(parent,
					form, form.getTitle(), JOptionPane.OK_CANCEL_OPTION)) {
				if (form.checkData()) {
					return (T) form.getResult();
				}
				JOptionPane.showMessageDialog(parent,
						TextUtil.get("error.empty"));
			} else {
				return null;
			}
		} while (true);
	}
}
