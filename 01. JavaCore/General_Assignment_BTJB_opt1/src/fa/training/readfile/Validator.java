package fa.training.readfile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String PHONE_REGEX = "[0-9]{10}";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    /**
     * Check phone format is valid
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
	Pattern pattern = Pattern.compile(PHONE_REGEX);
	Matcher matcher = pattern.matcher(phone);
	return matcher.matches();
    }
    
    /**
     * Check email format value is valid
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
	Pattern pattern = Pattern.compile(EMAIL_REGEX);
	Matcher matcher = pattern.matcher(email);
	return matcher.matches();
    }
}
