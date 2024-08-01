package fa.training.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String VALID_ISBN_REGEX = "\\d[-\\d]{8,15}\\d";
    
    /**
     * The length of isbn is in the 10-17 digit number and the ‘-‘ quote. Ex: 678-3-16-1486
     * @param isbn
     * @return
     */
    public static boolean isIsbn(String isbn) {
	Pattern pattern = Pattern.compile(VALID_ISBN_REGEX);
	Matcher matcher = pattern.matcher(isbn);
	return matcher.matches();
    }
}
