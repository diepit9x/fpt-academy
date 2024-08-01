package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Candidate;
import fa.training.entities.Certificate;
import fa.training.entities.Experience;
import fa.training.entities.Fresher;
import fa.training.entities.Intern;
import fa.training.util.Constant;
import fa.training.util.DBUtils;
import fa.training.util.SQLCommand;

public class CandidateDAOImpl implements CandidateDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ExperienceDAO experienceDAO;
    private FresherDAO fresherDAO;
    private InternDAO internDAO;
    private CertificateDAO certificateDAO;

    @Override
    public boolean addCandidate(Candidate candidate) throws Exception {
	boolean status = false;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(SQLCommand.CANDIDATE_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    resultSet = preparedStatement.executeQuery();
	    resultSet.moveToInsertRow();
	    resultSet.updateString("fullName", candidate.getFullName());
	    resultSet.updateDate("birthDay", candidate.getBirthDay());
	    resultSet.updateString("phone", candidate.getPhone());
	    resultSet.updateString("email", candidate.getEmail());
	    resultSet.updateInt("candidateType", candidate.getCandidateType());
	    resultSet.insertRow();
	    // get new id value
	    resultSet.last();
	    int candidateId = resultSet.getInt("candidateId");
	    candidate.setCandidateId(candidateId);
	    //add certificates
	    if (candidate.getCertificates() != null) {
		certificateDAO = new CertificateDAOImpl();
		    certificateDAO.addCertificate(connection, candidateId, candidate.getCertificates());
	    }
	    if (candidate instanceof Experience) {
		experienceDAO = new ExperienceDAOImpl();
		experienceDAO.addExperience(connection, candidate);
	    } else if (candidate instanceof Fresher) {
		fresherDAO = new FresherDAOImpl();
		fresherDAO.addFresher(connection, candidate);
	    } else if (candidate instanceof Intern) {
		internDAO = new InternDAOImpl();
		internDAO.addIntern(connection, candidate);
	    } else {
		throw new Exception(Constant.DEFAULT_EXCEPTION_MESSAGE);
	    }
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
		if (connection != null) {
		    connection.close();
		}
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
    public boolean updateCandidate(Candidate candidate) throws Exception {
	boolean status = false;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(SQLCommand.CANDIDATE_QUERY_UPDATE,
		    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    preparedStatement.setInt(1, candidate.getCandidateId());
	    resultSet = preparedStatement.executeQuery();

	    while (resultSet.next()) {
		resultSet.updateString("fullName", candidate.getFullName());
		resultSet.updateDate("birthDay", candidate.getBirthDay());
		resultSet.updateString("phone", candidate.getPhone());
		resultSet.updateString("email", candidate.getEmail());
		resultSet.updateRow();
	    }
	    if (candidate instanceof Experience) {
		experienceDAO = new ExperienceDAOImpl();
		experienceDAO.updateExperience(connection, candidate);
	    } else if (candidate instanceof Fresher) {
		fresherDAO = new FresherDAOImpl();
		fresherDAO.updateFresher(connection, candidate);
	    } else if (candidate instanceof Intern) {
		internDAO = new InternDAOImpl();
		internDAO.updateIntern(connection, candidate);
	    } else {
		throw new Exception(Constant.DEFAULT_EXCEPTION_MESSAGE);
	    }
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
		if (connection != null) {
		    connection.close();
		}
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
    public List<Candidate> getAllCandidate() throws Exception {
	List<Candidate> candidates = new ArrayList<>();
	Candidate candidate = null;
	int candidateType;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(SQLCommand.CANDIDATE_QUERY_GET_ALL);
	    resultSet = preparedStatement.executeQuery();
	    
	    certificateDAO = new CertificateDAOImpl();
	    while (resultSet.next()) {
		candidateType = resultSet.getInt("candidateType");
		if (candidateType == 0) {
		    candidate = new Experience();
		} else if (candidateType == 1) {
		    candidate = new Fresher();
		} else {
		    candidate = new Intern();
		}
		//common fields
		candidate.setCandidateId(resultSet.getInt("candidateId"));
		candidate.setFullName(resultSet.getString("fullName"));
		candidate.setBirthDay(resultSet.getDate("birthDay"));
		candidate.setPhone(resultSet.getString("phone"));
		candidate.setEmail(resultSet.getString("email"));
		candidate.setCandidateType(resultSet.getInt("candidateType"));
		
		if (candidateType == 0) {
		    ((Experience) candidate).setExpInYear(resultSet.getInt("expInYear"));
		    ((Experience) candidate).setProSkill(resultSet.getString("proSkill"));
		} else if (candidateType == 1) {
		    ((Fresher) candidate).setGraduationDate(resultSet.getDate("graduationDate"));
		    ((Fresher) candidate).setGraduationRank(resultSet.getInt("graduationRank"));
		    ((Fresher) candidate).setEducation(resultSet.getString("education"));
		} else {
		    ((Intern) candidate).setMajor(resultSet.getString("major"));
		    ((Intern) candidate).setSemester(resultSet.getInt("semester"));
		    ((Intern) candidate).setUniversityName(resultSet.getString("universityName"));
		}
		
		List<Certificate> certificates= certificateDAO.getCertificates(connection, candidate.getCandidateId());
		candidate.setCertificates(certificates);
		candidates.add(candidate);
	    }
	    connection.commit();
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
		if (connection != null) {
		    connection.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return candidates;
    }

    @Override
    public Candidate getCandidateByCandidateId(int candidateId) throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

}
