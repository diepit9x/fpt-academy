/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 27, 2024
 */
package fa.training.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static DBUtils instance;
    private Connection connection;
    private static String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=FINAL_DB;encrypt=true;trustServerCertificate=true;";
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
}
