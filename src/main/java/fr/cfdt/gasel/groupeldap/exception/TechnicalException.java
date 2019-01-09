package fr.cfdt.gasel.groupeldap.exception;

/**
 * The type Technical exception.
 */
public class TechnicalException extends Exception {

    /**
     * Instantiates a new Technical exception.
     *
     * @param message the message
     */
    public TechnicalException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Technical exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
