package fa.training.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fa.training.entities.Airplane;

public class Validator {
    private static final int VALID_MODEL_SIZE = 40;
    private static final String[] VALID_PLANE_TYPE = {"CAG", "LGR", "PRV"};
    private static final Map<String, String> VALID_ID_REGEX;
    private static final Map<String, Double> VALID_WEIGHT_TIMES;
    
    static {
	//valid of id
	Map<String, String> validId = new HashMap<>();
	validId.put("Fixedwing","FW\\d{5}");
	validId.put("Helicopter","RW\\d{5}");
	validId.put("Airport","AP\\d{5}");
	validId.put("Airplane", "^(FW|RW)\\d{5}");
	VALID_ID_REGEX = Collections.unmodifiableMap(validId);
	//valid of weight times
	Map<String, Double> validWeight = new HashMap<>();
	validWeight.put("Helicopter", 1.5D);
	VALID_WEIGHT_TIMES = Collections.unmodifiableMap(validWeight);
    }
    
    /**
     * Check id format is valid
     * @param clazz
     * @param id
     * @return
     */
    public static boolean isId(Class<?> clazz, String id) {
	String className = clazz.getSimpleName();
	if (!VALID_ID_REGEX.containsKey(className)) {
	    return false;
	}
	Pattern pattern = Pattern.compile(VALID_ID_REGEX.get(className));
	Matcher matcher = pattern.matcher(id);
	return matcher.matches();
    }
    
    /**
     * Check MaxTakeOffWeight
     * @param airplane
     * @return
     */
    public static boolean isWeightTimes(Airplane airplane) {
	String className = airplane.getClass().getSimpleName();
	if (!VALID_WEIGHT_TIMES.containsKey(className)) {
	    return true;
	}
	return airplane.getMaxTakeOffWeight() <= airplane.getEmptyWeight() * VALID_WEIGHT_TIMES.get(className);
    }
    
    /**
     * Check size of model characters is valid
     * @param model
     * @return
     */
    public static boolean isModel(String model) {
	return model.length() <= VALID_MODEL_SIZE;
    }
    
    /**
     * Check planeType is valid
     * @param planeType
     * @return
     */
    public static boolean isPlaneType(String planeType) {
	return Arrays.asList(VALID_PLANE_TYPE).contains(planeType);
    }
    
    
}
