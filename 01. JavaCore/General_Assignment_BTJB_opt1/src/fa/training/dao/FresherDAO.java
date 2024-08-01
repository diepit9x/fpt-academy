package fa.training.dao;

import java.sql.Connection;
import java.sql.SQLException;

import fa.training.entities.Candidate;

public interface FresherDAO {
    
    boolean addFresher(Connection connection, Candidate candidate) throws SQLException;
    boolean updateFresher(Connection connection, Candidate candidate) throws SQLException;

}
