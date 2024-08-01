package fa.training.exception;

public class DataAlreadyExistException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for DataAlreadyExistException without Parameters
     */
    public DataAlreadyExistException() {
	super("Data already exist");
    }

    /**
     * Constructor for DataAlreadyExistException with message.
     * 
     * @param message
     */
    public DataAlreadyExistException(String message) {
	super(message);
    }
}
