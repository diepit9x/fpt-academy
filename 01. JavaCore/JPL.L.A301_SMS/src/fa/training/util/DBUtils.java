package fa.training.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBUtils {
    private static DBUtils instance;
    private Connection connection;
    private static String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=SMS;encrypt=true;trustServerCertificate=true;";
    private static String DB_USERNAME = "sa";
    private static String DB_PASSWORD = "123456789";
    
    private DBUtils() {
	try {
	    Class.forName(DB_DRIVER);
	    connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	} catch (ClassNotFoundException | SQLException e) {
	    System.out.println("Connect failure");
	    e.printStackTrace();
	}
    }
    
    /**
     * Get connection from the instance
     * @return
     */
    public Connection getConnection() {
	return connection;
    }

    /**
     * Create a new instance which connects with the database
     * @return
     * @throws SQLException
     */
    public static DBUtils getInstance() throws SQLException {
	if (instance == null || instance.getConnection().isClosed()) {
	    instance = new DBUtils();
	}
	return instance;
    }
    
    
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
