
package net.entrofi.commons.exception;

/**
 * This exception is the base exception class that will be used for exceptions which we will not proceed directly
 * from the rest facade. That is to say if you want to control exception flow extend (or use) this exception directly.
 */
public class TigerBusinessException extends Exception {


    public TigerBusinessException(String messageKey) {
        super(messageKey);
    }

    public TigerBusinessException(Throwable cause) {
        super(cause);
    }

    public TigerBusinessException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }

}
