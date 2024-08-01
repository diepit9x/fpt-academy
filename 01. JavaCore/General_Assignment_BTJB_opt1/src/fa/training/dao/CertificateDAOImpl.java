package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Certificate;
import fa.training.util.DBUtils;
import fa.training.util.SQLCommand;

public class CertificateDAOImpl implements CertificateDAO {
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public List<Certificate> getCertificates(Connection connection, int candidateId) throws SQLException {
	List<Certificate> certificates = new ArrayList<>();
	Certificate certificate = null;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(SQLCommand.CERTIFICATE_QUERY_BY_CANDIDATEID);
	    preparedStatement.setInt(1, candidateId);
	    resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
		certificate = new Certificate();
		certificate.setCertificateId(resultSet.getInt("certificateId"));
		certificate.setCertificateName(resultSet.getString("certificateName"));
		certificate.setCertificateRank(resultSet.getInt("certificateRank"));
		certificate.setCertificateDate(resultSet.getDate("certificateDate"));
		certificates.add(certificate);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    if (connection != null) {
		try {
		    connection.rollback();
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	    }
	} finally {
	    try {
		if (preparedStatement != null) {
		    preparedStatement.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return certificates;
    }

    @Override
    public boolean addCertificate(Connection connection, int candidateId, List<Certificate> certificates) throws SQLException {
	boolean status = false;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(SQLCommand.CERTIFICATE_QUERY,
		    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    resultSet = preparedStatement.executeQuery();
	    for (Certificate certificate : certificates) {
		resultSet.moveToInsertRow();
		resultSet.updateInt("certificateId", certificate.getCertificateId());
		resultSet.updateInt("candidateId", candidateId);
		resultSet.updateString("certificateName", certificate.getCertificateName());
		resultSet.updateInt("certificateRank", certificate.getCertificateRank());
		resultSet.updateDate("certificateDate", certificate.getCertificateDate());
		resultSet.insertRow();
	    }
	    status = true;
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (preparedStatement != null) {
		    preparedStatement.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return status;
    }

}
