package fa.training.exception;

public class DataNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for DataNotFoundException without Parameters
     */
    public DataNotFoundException() {
	super("Data not found");
    }

    /**
     * Constructor for DataNotFoundException with message.
     * 
     * @param message
     */
    public DataNotFoundException(String message) {
	super(message);
    }
}
