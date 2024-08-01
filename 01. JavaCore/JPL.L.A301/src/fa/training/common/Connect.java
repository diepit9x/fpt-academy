package fa.training.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=SMS;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123456789";

    public static void main(String[] args) {
	try {
	    Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery("select * from Customer");
	    while (rs.next()) {
		System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
	    }
	    // close connection
	    conn.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    public static Connection getConnection(String dbURL, String userName, String password) {
	Connection conn = null;
	try {
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    conn = DriverManager.getConnection(dbURL, userName, password);
	    System.out.println("connect successfully!");
	} catch (Exception ex) {
	    System.out.println("connect failure!");
	    ex.printStackTrace();
	}
	return conn;
    }

}
