package ru.sstu.sm.core.gui.forms;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.measure.quantity.Quantity;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ru.sstu.sm.core.domain.Value;

/**
 * <code>AbstractForm</code> class is abstract form for section properties
 * editing.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public abstract class AbstractForm extends JPanel {

	/**
	 * Default Insets for form subclasses.
	 */
	protected static final Insets INSETS = new Insets(5, 5, 5, 5);

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -832424760134969989L;

	/**
	 * Row index.
	 */
	private int rowIndex;

	/**
	 * Labels.
	 */
	private final List<JLabel> labels = new ArrayList<JLabel>();

	/**
	 * Physical values views.
	 */
	private final List<ValueView> views = new ArrayList<ValueView>();

	/**
	 *
	 */
	protected AbstractForm() {
		super(new GridBagLayout());
	}

	/**
	 * @return if data in the form fields are correct
	 */
	public boolean checkData() {
		for (ValueView view : views) {
			if (view.checkData()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Provides form title.
	 *
	 * @return title of the dialog
	 */
	public abstract String getTitle();

	/**
	 * Provides result.
	 *
	 * @return result entity of dialog
	 */
	public abstract Object getResult();

	/**
	 * Adds new physical value editor.
	 *
	 * @param description value description
	 * @param old         old value
	 */
	protected void addValue(String description, Value<?> old) {
		addValue(description, old, true);
	}

	/**
	 * Adds new physical value editor.
	 *
	 * @param description value description
	 * @param old         old value
	 * @param required    true if value is required
	 */
	protected void addValue(String description, Value<?> old,
			boolean required) {
		ValueView view = new ValueView(description, old, required);
		addValue(view);
	}

	/**
	 * Adds new physical value editor.
	 *
	 * @param view value view
	 */
	void addValue(ValueView view) {
		views.add(view);
		JLabel label = new JLabel(view.getDescription());
		labels.add(label);
		add(label, new GridBagConstraints(0, rowIndex, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				INSETS, 0, 0));
		add(view.getText(), new GridBagConstraints(1, rowIndex, 1, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, INSETS,
				0, 0));
		rowIndex++;
	}

	/**
	 * Adds component to form.
	 *
	 * @param component component
	 * @since SM 2.0
	 */
	protected void addComponent(JComponent component) {
		final int width = 3;
		add(component, new GridBagConstraints(0, rowIndex, width, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				INSETS, 0, 0));
	}

	/**
	 * Provides value.
	 *
	 * @param index value component index
	 * @return value provided by user
	 */
	protected final Value<? extends Quantity> getValue(int index) {
		return views.get(index).getValue();
	}

	/**
	 * Enables or disables component.
	 *
	 * @param index   index
	 * @param enabled enabled
	 * @since SM 2.0
	 */
	protected void setEnabled(int index, boolean enabled) {
		views.get(index).setEnabled(enabled);
	}

	/**
	 * Sets new field description.
	 * Note: {@link #setEnabled(boolean)} method should be called before this
	 * method.
	 *
	 * @param index       index
	 * @param description description
	 * @since SM 2.0
	 */
	protected void setDescription(int index, String description) {
		ValueView view = views.get(index);
		if ("".equals(description)) {
			labels.get(index).setText(" ");
		} else {
			view.setDescription(description);
			labels.get(index).setText(view.getDescription());
		}
	}

	/**
	 * Sets new value.
	 *
	 * @param index index
	 * @param value value
	 */
	protected void setValue(int index, Value<?> value) {
		views.get(index).setValue(value);
	}
}
