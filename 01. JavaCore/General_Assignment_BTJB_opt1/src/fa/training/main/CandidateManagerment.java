package fa.training.main;

import java.sql.Connection;
import java.sql.SQLException;

import fa.training.util.DBUtils;

public class CandidateManagerment {

public static void main(String[] args) throws SQLException {
    Connection connection = DBUtils.getInstance().getConnection();
    if (connection != null) {
	System.out.println("ket noi thanh cong");
    } else {
	System.out.println("ket noi that bai");
    }
}

}
