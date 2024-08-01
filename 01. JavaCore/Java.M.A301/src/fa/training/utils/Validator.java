package fa.training.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String VALID_EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    /**
     * Check date format is valid
     * @param date
     * @return
     */
    public static boolean isDateFormat(String date) {
	return isValidDate(date, "dd/MM/yyyy");
    }
    
    private static boolean isValidDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false); // Ensure strict parsing
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    /**
     * Check email format is valid
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
	Pattern pattern = Pattern.compile(VALID_EMAIL_REGEX);
	Matcher matcher = pattern.matcher(email);
	return matcher.matches();
    }
    
   /**
    * Check number range is valid
    * @param range
    * @return
    */
    public static boolean numberRange(double range) {
	return range >= 0 && range <= 10D;
    }
    
}
