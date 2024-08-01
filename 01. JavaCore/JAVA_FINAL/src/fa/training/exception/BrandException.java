package fa.training.exception;

public class BrandException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     *  Constructor for BrandException without Parameters.
     */
    public BrandException() {
	super("Branch must be in the list of Dell, Asus, ViewSonic, Lenovo and Gigabyte!!!");
    }

    /**
     * Constructor for BrandException with message.
     * 
     * @param message
     */
    public BrandException(String message) {
	super(message);
    }
}