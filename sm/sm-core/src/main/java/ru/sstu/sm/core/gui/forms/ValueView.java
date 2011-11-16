package ru.sstu.sm.core.gui.forms;

import javax.swing.JComponent;
import javax.swing.JTextField;

import ru.sstu.sm.core.domain.Value;
import ru.sstu.sm.core.util.TextUtil;

/**
 * <code>ValueView</code> class helps to add controls operating with physical
 * values management to forms.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
final class ValueView {

	/**
	 * Text field.
	 */
	private final JTextField text = new JTextField(15);

	/**
	 * Description.
	 */
	private String description;

	/**
	 * Value.
	 */
	private Value<?> value;

	/**
	 * Is value required.
	 */
	private boolean required = true;

	/**
	 * Initializes physical value view.
	 *
	 * @param description description
	 * @param value       initial value
	 */
	ValueView(String description, Value<?> value) {
		this(description, value, true);
	}

	/**
	 * Initializes physical value view.
	 *
	 * @param description description
	 * @param value       initial value
	 * @param required    true if validation is required
	 */
	ValueView(String description, Value<?> value, boolean required) {
		this.description = description;
		this.value = value;
		Number number = value.getValue();
		if (value.getValue() != null && number.doubleValue() != 0.0) {
			text.setText(String.valueOf(value.getValue()));
		}
		this.required = required;
	}

	/**
	 * @return description label
	 */
	String getDescription() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(description).append(", ")
				.append(TextUtil.get(value.getUnit()));
		return buffer.toString();
	}

	/**
	 * @return text field object
	 */
	JComponent getText() {
		return text;
	}

	/**
	 * @return entered physical value
	 */
	Value<?> getValue() {
		return value;
	}

	/**
	 * @return if entered data is not correct
	 */
	boolean checkData() {
		try {
			if (!text.isEnabled()) {
				return false;
			}
			value.setValue(Double.parseDouble(text.getText()
					.replace(',', '.')));
			return false;
		} catch (NumberFormatException ignored) {
			value.setValue(0.0);
			return required;
		}
	}

	/**
	 * @param description the description to set
	 */
	void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets new value.
	 *
	 * @param value value
	 * @since SM 2.0
	 */
	void setValue(Value<?> value) {
		this.value = value;
		text.setText(String.valueOf(value.getValue()));
	}

	/**
	 * Enables or disables component.
	 *
	 * @param enabled enabled
	 * @since SM 2.0
	 */
	void setEnabled(boolean enabled) {
		text.setEditable(enabled);
		text.setVisible(enabled);
	}
}
