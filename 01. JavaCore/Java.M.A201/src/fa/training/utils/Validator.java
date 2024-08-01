package fa.training.utils;

import java.util.Arrays;
public class Validator {
    private static final String VALID_COURSECODE_REGEX = "FW\\d{3}";
    private static final String[] VALID_STATUS_ARRAY = {"active", "in-active"};
    private static final String[] VALID_FLAG_ARRAY = {"optional", "mandatory", "N/A"};
    
    /**
     * Check course code format is valid
     * @param courseCode
     * @return
     */
    public static boolean courseCode(String courseCode) {
        return courseCode.toUpperCase().matches(VALID_COURSECODE_REGEX);
    }

    /**
     * Check status format is valid
     * @param status
     * @return
     */
    public static boolean status(String status) {
        return Arrays.asList(VALID_STATUS_ARRAY).contains(status);
    }

    /**
     * Check flag format is valid
     * @param flag
     * @return
     */
    public static boolean flag(String flag) {
        return Arrays.asList(VALID_FLAG_ARRAY).contains(flag);
    }
}
