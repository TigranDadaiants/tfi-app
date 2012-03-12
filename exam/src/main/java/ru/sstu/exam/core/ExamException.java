package ru.sstu.exam.core;

/**
 * <code>ExamException</code> class is just standard exception for Exam project.
 *
 * @author Denis_Murashev
 * @since Exam 1.0
 */
public class ExamException extends Exception {

	private static final long serialVersionUID = 1603124377177036178L;

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param   message   the detail message. The detail message is saved for
	 *          later retrieval by the {@link #getMessage()} method.
	 */
	public ExamException(String message) {
		super(message);
	}
}
