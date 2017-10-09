

package net.entrofi.commons.exception;

/**
 * The intended usage of this exception is to redirect errors from all layers to rest facade directly. Throwers of
 * this exception must not catch it and pass the error message directly to consumers of the facade. It may be handled
 * at servlet layer via aspects or servlet yet, it should not be prefered. Same rules applies to extenders of this
 * exception.
 */
public class TigerRuntimeException extends RuntimeException {

    public TigerRuntimeException(String messageKey) {
        super(messageKey);
    }

    public TigerRuntimeException(Throwable cause) {
        super(cause);
    }
    public TigerRuntimeException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }
}
