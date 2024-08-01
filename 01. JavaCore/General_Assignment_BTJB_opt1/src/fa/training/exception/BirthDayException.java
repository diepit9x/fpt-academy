package fa.training.exception;

public class BirthDayException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for BirthDayException without Parameters
     */
    public BirthDayException() {
	super();
    }

    /**
     * Constructor for BirthDayException with message.
     * 
     * @param message
     */
    public BirthDayException(String message) {
	super(message);
    }
}