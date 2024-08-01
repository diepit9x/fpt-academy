package fa.training.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Candidate;
import fa.training.entities.Interview;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class InterviewDAOImpl implements InterviewDAO {

	@Override
	public Interview getInterviewByID(int interviewId) throws HibernateException {
		if (interviewId <= 0) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Interview.class, interviewId);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Interview> getAllInterviews() throws HibernateException {
		List<Interview> interviews = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Interview> query = session.createQuery("FROM Interview", Interview.class);
			interviews = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return interviews;
	}

	@Override
	public boolean updateInterviewByID(int interviewId, Interview interview)
			throws HibernateException, DataNotFoundException {
		if (interviewId <= 0 || interview == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Interview exstingInterview = getInterviewByID(interviewId);
			if (exstingInterview == null) {
				throw new DataNotFoundException();
			}
			interview.setInterviewId(exstingInterview.getInterviewId());
			session.merge(interview);
			
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteInterviewByID(int interviewId) throws HibernateException, DataNotFoundException {
		if (interviewId <= 0) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Interview exstingInterview = getInterviewByID(interviewId);
			if (exstingInterview == null) {
				throw new DataNotFoundException();
			}
			session.remove(exstingInterview);
			
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean insertInterview(Interview interview) throws HibernateException, DataAlreadyExistException {
		if (interview == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Interview exstingInterview = getInterviewByID(interview.getInterviewId());
			if (exstingInterview != null) {
				throw new DataAlreadyExistException();
			}
			session.merge(interview);
			
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Candidate> findByInterviewResultAndDate(String interviewResult, LocalDate date)
			throws HibernateException {
		List<Candidate> candidates = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Candidate> criteria = criteriaBuilder.createQuery(Candidate.class);
			Root<Interview> root = criteria.from(Interview.class);
			criteria.select(root.get("candidate"));
			//Tao nhieu dieu kien
			Predicate candidateCondition = criteriaBuilder.isNotNull(root.get("candidate"));
			Predicate resultCondition = criteriaBuilder.equal(root.get("interviewResult"), interviewResult);
			Predicate dateCondition = criteriaBuilder.equal(root.get("date"), date);
			//dua vao truy van
			criteria.where(criteriaBuilder.and(candidateCondition, resultCondition, dateCondition));
			candidates = session.createQuery(criteria).getResultList();
			
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return candidates;
	}
}
