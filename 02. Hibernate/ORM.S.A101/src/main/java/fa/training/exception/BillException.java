package fa.training.exception;

public class BillException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for BillException without Parameters
     */
    public BillException() {
	super();
    }

    /**
     * Constructor for BillException with message.
     * 
     * @param message
     */
    public BillException(String message) {
	super(message);
    }
}
