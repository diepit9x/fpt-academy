package fa.training.exceptions;

public class InvalidDataException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for InvalidDataException without Parameters
     */
    public InvalidDataException() {
	super();
    }
    
    /**
     * Constructor for InvalidDataException with message.
     * @param message
     */
    public InvalidDataException(String message) {
	super(message);
    }
}
