package fa.training.exception;

public class TypeException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     *  Constructor for TypeException without Parameters.
     */
    public TypeException() {
	super("Type is invalid. Must be 1, 2, 3");
    }

    /**
     * Constructor for TypeException with message.
     * 
     * @param message
     */
    public TypeException(String message) {
	super(message);
    }
}