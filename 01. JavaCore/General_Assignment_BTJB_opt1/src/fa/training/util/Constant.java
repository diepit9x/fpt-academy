package fa.training.util;

public class Constant {
    public static final String DATE_PATTERN = "^(0[1-9]|[12][0-9]|3[01])(\\/)(0[1-9]|1[0-2])(\\/)\\d{4}$";
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    //Exception
    public static final String DEFAULT_EXCEPTION_MESSAGE = "The system has encountered an unexpected problem, sincerely sorry !!!";
    public static final String BIRTHDAY_EXCEPTION_MESSAGE = "Birthday format is invalid.";
    public static final String EMAIL_EXCEPTION_MESSAGE = "Email format is ivalid";
    public static final String DATE_EXCEPTION_MESSAGE = "Date format is invalid.";

}