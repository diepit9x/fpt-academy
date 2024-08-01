package fa.training.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connect {
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=SMS;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123456789";

    public static void main(String[] args) {
	try {
	    Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery("select * from [Order]");
//	    while (rs.next()) {
//		System.out.println(rs.getInt(1) + "  " + rs.getString(2) );
//	    }

	    printResultSet(rs);
	    // close connection
	    conn.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    /**
     * Get connection from the instance
     * 
     * @param dbURL
     * @param userName
     * @param password
     * @return
     */
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

    public static void printResultSet(ResultSet resultSet) throws SQLException {
	ResultSetMetaData metaData = resultSet.getMetaData();
	int columnCount = metaData.getColumnCount();

	// Determine the width of each column
	List<Integer> columnWidths = new ArrayList<>();
	for (int i = 1; i <= columnCount; i++) {
	    columnWidths.add(metaData.getColumnName(i).length());
	}

	// Process each row to find the maximum width of each column
	List<String[]> rows = new ArrayList<>();
	while (resultSet.next()) {
	    String[] row = new String[columnCount];
	    for (int i = 1; i <= columnCount; i++) {
		String value = resultSet.getString(i);
		row[i - 1] = value;
		columnWidths.set(i - 1, Math.max(columnWidths.get(i - 1), value.length()));
	    }
	    rows.add(row);
	}

	// Print the column names
	for (int i = 1; i <= columnCount; i++) {
	    System.out.printf("%-" + columnWidths.get(i - 1) + "s ", metaData.getColumnName(i));
	}
	System.out.println();

	// Print a separator line
	for (int width : columnWidths) {
	    System.out.print("-".repeat(width) + " ");
	}
	System.out.println();

	// Print each row
	for (String[] row : rows) {
	    for (int i = 0; i < columnCount; i++) {
		System.out.printf("%-" + columnWidths.get(i) + "s ", row[i]);
	    }
	    System.out.println();
	}
    }

}
