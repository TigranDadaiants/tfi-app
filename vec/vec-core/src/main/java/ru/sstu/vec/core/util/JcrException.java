package ru.sstu.vec.core.util;

/**
 * {@code JcrException} represents an exception used to wrap exceptions of JCR.
 *
 */
public class JcrException extends RuntimeException {

    private static final long serialVersionUID = -1995544879502909121L;

    public JcrException() {
        super();
    }

    public JcrException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public JcrException(String message, Throwable cause) {
        super(message, cause);
    }

    public JcrException(String message) {
        super(message);
    }

    public JcrException(Throwable cause) {
        super(cause);
    }

}
