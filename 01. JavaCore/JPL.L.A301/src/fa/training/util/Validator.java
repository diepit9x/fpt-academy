package fa.training.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static Matcher matcher;
    private static Pattern pattern;

    /**
     * This method check format of date value
     * 
     * @param String date
     * @return boolean
     */
    public static boolean isDate(String date) {
	pattern = Pattern.compile(Constant.DATE_PATTERN);
	matcher = pattern.matcher(date);
	return matcher.matches();
    }

}
