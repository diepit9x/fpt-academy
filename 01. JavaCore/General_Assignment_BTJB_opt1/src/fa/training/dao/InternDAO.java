package fa.training.dao;

import java.sql.Connection;
import java.sql.SQLException;

import fa.training.entities.Candidate;

public interface InternDAO {
    boolean addIntern(Connection connection, Candidate candidate) throws SQLException;
    boolean updateIntern(Connection connection, Candidate candidate) throws SQLException;

}
