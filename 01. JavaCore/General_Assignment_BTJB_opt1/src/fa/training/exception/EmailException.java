package fa.training.exception;

public class EmailException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for EmailException without Parameters
     */
    public EmailException() {
	super();
    }

    /**
     * Constructor for EmailException with message.
     * 
     * @param message
     */
    public EmailException(String message) {
	super(message);
    }
}