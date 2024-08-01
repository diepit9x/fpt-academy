package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fa.training.entities.Candidate;
import fa.training.entities.Fresher;
import fa.training.util.DBUtils;
import fa.training.util.SQLCommand;

public class FresherDAOImpl implements FresherDAO {
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public boolean addFresher(Connection connection, Candidate candidate) throws SQLException {
	boolean status = false;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(
		    SQLCommand.FRESHER_QUERY,
		    ResultSet.TYPE_SCROLL_SENSITIVE, 
		    ResultSet.CONCUR_UPDATABLE);
	    resultSet = preparedStatement.executeQuery();
	    resultSet.moveToInsertRow();
	    resultSet.updateInt("candidateId", candidate.getCandidateId());
	    resultSet.updateDate("graduationDate", ((Fresher) candidate).getGraduationDate());
	    resultSet.updateInt("graduationRank", ((Fresher) candidate).getGraduationRank());
	    resultSet.updateString("education", ((Fresher) candidate).getEducation());
	    resultSet.insertRow();
	    connection.commit();
	    status = true;
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
	return status;
    }

    @Override
    public boolean updateFresher(Connection connection, Candidate candidate) throws SQLException {
	boolean status = false;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(
		    SQLCommand.FRESHER_QUERY_UPDATE,
		    ResultSet.TYPE_SCROLL_SENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    preparedStatement.setInt(1, candidate.getCandidateId());
	    resultSet = preparedStatement.executeQuery();
	    
	    while (resultSet.next()) {
		    resultSet.updateDate("graduationDate", ((Fresher) candidate).getGraduationDate());
		    resultSet.updateInt("graduationRank", ((Fresher) candidate).getGraduationRank());
		    resultSet.updateString("education", ((Fresher) candidate).getEducation());
		    resultSet.updateRow();
	    }
	    status = true;
	} catch(SQLException e) {
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