package fa.training.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String VALID_PHONENUMBER_REGEX = "^0[3-9]\\d{8}$";
    
    /**
     * Check phone number format is valid
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
	Pattern pattern = Pattern.compile(VALID_PHONENUMBER_REGEX);
	Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    
    /**
     * Check order number format is valid
     * @param orderNumber
     * @return
     */
    public static boolean isOrderNumber(String orderNumber) {
	return orderNumber.length() == 10;
    }
}
