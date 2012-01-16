package ru.sstu.sms.service.model;

import java.io.Serializable;

/**
 * <code>CheckableObject</code> class is most common wrapper for checkable
 * objects.
 *
 * @author Denis_Murashev
 *
 * @param <T> wrapped object type
 * @since SMS 1.0
 */
public class CheckableObject<T> implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 8813315213748796690L;

	/**
	 * Wrapped object.
	 */
	private final T object;

	/**
	 * Checked flag.
	 */
	private boolean checked;

	/**
	 * @param object  wrapped object
	 * @param checked default checked flag
	 */
	public CheckableObject(T object, boolean checked) {
		this.object = object;
		this.checked = checked;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the object
	 */
	public T getObject() {
		return object;
	}
}
