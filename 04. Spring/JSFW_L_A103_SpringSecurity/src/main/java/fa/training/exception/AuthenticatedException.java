package fa.training.exception;

public class AuthenticatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AuthenticatedException(String message) {
	super(message);
    }

    public AuthenticatedException() {
	super("Not authenticated");
    }
}