package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fa.training.entities.Candidate;
import fa.training.entities.Experience;
import fa.training.util.DBUtils;
import fa.training.util.SQLCommand;

public class ExperienceDAOImpl implements ExperienceDAO {
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public boolean addExperience(Connection connection, Candidate candidate) throws SQLException {
	boolean status = false;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(
		    SQLCommand.EXPERIENCE_QUERY,
		    ResultSet.TYPE_SCROLL_SENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    resultSet = preparedStatement.executeQuery();
	    resultSet.moveToInsertRow();
	    resultSet.updateInt("candidateId", candidate.getCandidateId());
	    resultSet.updateInt("expInYear", ((Experience) candidate).getExpInYear());
	    resultSet.updateString("proSkill", ((Experience) candidate).getProSkill());
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
    public boolean updateExperience(Connection connection, Candidate candidate) throws SQLException {
	boolean status = false;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(
		    SQLCommand.EXPERIENCE_QUERY_UPDATE,
		    ResultSet.TYPE_SCROLL_SENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    preparedStatement.setInt(1, candidate.getCandidateId());
	    resultSet = preparedStatement.executeQuery();
	    
	    while (resultSet.next()) {
		    resultSet.updateInt("expInYear", ((Experience) candidate).getExpInYear());
		    resultSet.updateString("proSkill", ((Experience) candidate).getProSkill());
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