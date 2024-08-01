package fa.training.dao;

import java.sql.Connection;
import java.sql.SQLException;

import fa.training.entities.Candidate;

public interface ExperienceDAO {
    boolean addExperience(Connection connection, Candidate candidate) throws SQLException;
    boolean updateExperience(Connection connection, Candidate candidate) throws SQLException;

}
