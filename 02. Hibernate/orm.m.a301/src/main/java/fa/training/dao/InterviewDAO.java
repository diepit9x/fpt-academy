package fa.training.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Candidate;
import fa.training.entities.Interview;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface InterviewDAO {
	Interview getInterviewByID(int interviewId) throws HibernateException;
	List<Candidate> findByInterviewResultAndDate(String interviewResult, LocalDate date) throws HibernateException;
	List<Interview> getAllInterviews() throws HibernateException;
	boolean updateInterviewByID(int interviewId, Interview interview) throws HibernateException, DataNotFoundException;
	boolean deleteInterviewByID(int interviewId) throws HibernateException, DataNotFoundException;
	boolean insertInterview(Interview interview) throws HibernateException, DataAlreadyExistException;
}
