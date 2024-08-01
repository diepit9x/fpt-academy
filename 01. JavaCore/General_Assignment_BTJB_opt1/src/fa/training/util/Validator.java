package fa.training.util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    
    /**
     * Check date format value is valid
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
	try {
            LocalDate birthDate = LocalDate.parse(date);
            LocalDate minDate = LocalDate.of(1900, 1, 1);
            LocalDate maxDate = LocalDate.now();
            return (birthDate.isAfter(minDate) || birthDate.isEqual(minDate)) && (birthDate.isBefore(maxDate) || birthDate.isEqual(maxDate));
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check email format value is valid
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
	Pattern pattern = Pattern.compile(Constant.EMAIL_PATTERN);
	Matcher matcher = pattern.matcher(email);
	return matcher.matches();
    }
}
