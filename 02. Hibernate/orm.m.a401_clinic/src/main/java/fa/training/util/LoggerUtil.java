package fa.training.util;

import org.apache.log4j.Logger;

public class LoggerUtil {

    public static Logger getLogger() {
        String className = new Exception().getStackTrace()[1].getClassName();
        return Logger.getLogger(className);
    }
}