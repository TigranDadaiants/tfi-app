package ru.sstu.sm.core.service;

/**
 * Class <code>SMException</code> is exception class for Strength of Materials
 * project.
 *
 * @author Denis A. Murashev
 * @since SM 1.0
 */
public final class SMException extends Exception {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -2719539169566040272L;

	/**
	 * Constructs a new exception with the specified cause and a detail
	 * message of <tt>(cause==null ? null : cause.toString())</tt> (which
	 * typically contains the class and detail message of <tt>cause</tt>).
	 * This constructor is useful for exceptions that are little more than
	 * wrappers for other throwables.
	 *
	 * @param throwable the cause (which is saved for later retrieval by the
	 *                  {@link #getCause()} method).  (A <tt>null</tt> value is
	 *                  permitted, and indicates that the cause is nonexistent
	 *                  or unknown.)
	 */
	public SMException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public SMException(String message) {
		super(message);
	}
}
