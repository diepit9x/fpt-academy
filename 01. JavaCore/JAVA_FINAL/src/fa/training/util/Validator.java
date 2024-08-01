/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 28, 2024
 */
package fa.training.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class Validator.
 */
public class Validator {
    private static Matcher matcher;
    private static Pattern pattern;
    
    
    public static boolean isType(int type) {
	return type >= 1 && type <= 3;
    }
    
    /**
     * Checks if is monitor id.
     *
     * @param monitorId the monitor id
     * @return true, if is monitor id
     */
    public static boolean isMonitorId(String monitorId) {
	pattern = Pattern.compile(Constant.MONITORID_REGEX);
	matcher = pattern.matcher(monitorId);
	return matcher.matches();
    }
    
    /**
     * Checks if is brand.
     *
     * @param brand the brand
     * @return true, if is brand
     */
    public static boolean isBrand(String brand) {
	return brand.equalsIgnoreCase("Dell") || brand.equalsIgnoreCase("HP") || brand.equalsIgnoreCase("Asus") || brand.equalsIgnoreCase("viewSonic") || brand.equalsIgnoreCase("Lenovo") || brand.equalsIgnoreCase("Gigabyte");
    }
}
