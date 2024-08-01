package fa.training.util;

/**
 * The Class Validator.
 */
public class Validator {
	
	/**
	 * Checks if is seat status.
	 *
	 * @param status the status
	 * @return true, if is seat status
	 */
	public static boolean isSeatStatus(String status) {
		return status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Not Available") || status.equalsIgnoreCase("Booked");
	}
	
	/**
	 * Checks if is seat type.
	 *
	 * @param type the type
	 * @return true, if is seat type
	 */
	public static boolean isSeatType(String type) {
		return type.equalsIgnoreCase("VIP") || type.equalsIgnoreCase("Normal");
	}
}
