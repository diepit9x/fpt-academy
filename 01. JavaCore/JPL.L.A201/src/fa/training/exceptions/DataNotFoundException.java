package fa.training.exceptions;

public class DataNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for IncorrectFomartException without Parameters
     */
    public DataNotFoundException() {
	super();
    }
    
    /**
     * Constructor for IncorrectFomartException with message.
     * @param message
     */
    public DataNotFoundException(String message) {
	super(message);
    }

}
