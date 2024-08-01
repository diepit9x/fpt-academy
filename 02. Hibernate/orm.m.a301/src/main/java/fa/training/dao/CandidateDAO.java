package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Candidate;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface CandidateDAO {
	List<Candidate> findBySkillAndLevel(String skill, int level) throws HibernateException;
	List<Candidate> findBySkillAndLanguage(String skill, String foreignLanguage) throws HibernateException;
	List<Candidate> pagingCandidate(int pageNumber, int pageSize) throws HibernateException;
	boolean updateRemark() throws HibernateException;
	
	Candidate getCandidateByID(int candidateId) throws HibernateException;
	List<Candidate> getAllCandidates() throws HibernateException;
	boolean updateCandidateByID(int candidateId, Candidate candidate) throws HibernateException, DataNotFoundException;
	boolean deleteCandidateByID(int candidateId) throws HibernateException, DataNotFoundException;
	boolean insertCandidate(Candidate candidate) throws HibernateException, DataAlreadyExistException;
}
