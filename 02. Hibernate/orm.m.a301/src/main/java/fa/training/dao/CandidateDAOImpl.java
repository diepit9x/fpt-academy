package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Candidate;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;
import fa.training.util.HibernateValidator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CandidateDAOImpl implements CandidateDAO {

	@Override
	public Candidate getCandidateByID(int candidateId) throws HibernateException {
		if (candidateId <= 0) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Candidate.class, candidateId);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Candidate> getAllCandidates() throws HibernateException {
		List<Candidate> candidates = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Candidate> query = session.createQuery("FROM Candidate", Candidate.class);
			candidates = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return candidates;
	}

	@Override
	public boolean updateCandidateByID(int candidateId, Candidate candidate)
			throws HibernateException, DataNotFoundException {
		if (candidateId <= 0 || candidate == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Candidate existingCandidate = getCandidateByID(candidateId);
			if (existingCandidate == null) {
				throw new DataNotFoundException();
			}
			candidate.setCandidateId(candidateId);
			List<String> errorMessages = HibernateValidator.getViolations(candidate);
			if (errorMessages.isEmpty()) {
				session.merge(candidate);
			} else {
				errorMessages.forEach(System.err::println);
				return false;
			}
			
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteCandidateByID(int candidateId) throws HibernateException, DataNotFoundException {
		if (candidateId <= 0) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Candidate existingCandidate = getCandidateByID(candidateId);
			if (existingCandidate == null) {
				throw new DataNotFoundException();
			}
			session.remove(existingCandidate);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean insertCandidate(Candidate candidate) throws HibernateException, DataAlreadyExistException {
		if (candidate == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Candidate existingCandidate = getCandidateByID(candidate.getCandidateId());
			if (existingCandidate != null) {
				throw new DataAlreadyExistException();
			}
			List<String> errorMessages = HibernateValidator.getViolations(candidate);
			if (errorMessages.isEmpty()) {
				session.merge(candidate);
			} else {
				errorMessages.forEach(System.err::println);
				return false;
			}
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Candidate> findBySkillAndLevel(String skill, int level) throws HibernateException {
		List<Candidate> candidates = new ArrayList<>();
		if (skill == null || level < 1 || level > 7) {
			return candidates;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Candidate> query = session.createQuery("FROM Candidate c WHERE c.skill = :skill AND c.level = :level", Candidate.class);
			query.setParameter("skill", skill);
			query.setParameter("level", level);
			candidates = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return candidates;
	}

	@Override
	public List<Candidate> findBySkillAndLanguage(String skill, String foreignLanguage) throws HibernateException {
		List<Candidate> candidates = new ArrayList<>();
		if (skill == null || foreignLanguage == null) {
			return candidates;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Candidate> query = session.createQuery("FROM Candidate c WHERE c.skill = :skill AND c.foreignLanguage = :foreignLanguage", Candidate.class);
			query.setParameter("skill", skill);
			query.setParameter("foreignLanguage", foreignLanguage);
			candidates = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return candidates;
	}

	@Override
	public boolean updateRemark() throws HibernateException {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaUpdate<Candidate> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Candidate.class);
			Root<Candidate> root = criteriaUpdate.from(Candidate.class);
			criteriaUpdate.set("remark", "inactive");
			
			Predicate cvEmpty = criteriaBuilder.or(
					criteriaBuilder.isNull(root.get("cv")),
					criteriaBuilder.equal(criteriaBuilder.trim(criteriaBuilder.lower(root.get("cv"))), "")
					);
			Predicate emailEmpty = criteriaBuilder.or(
					criteriaBuilder.isNull(root.get("email")),
					criteriaBuilder.equal(criteriaBuilder.trim(criteriaBuilder.lower(root.get("email"))), "")
					);
			Predicate phoneEmpty = criteriaBuilder.or(
					criteriaBuilder.isNull(root.get("phone")),
					criteriaBuilder.equal(criteriaBuilder.trim(criteriaBuilder.lower(root.get("phone"))), "")
					);
			criteriaUpdate.where(criteriaBuilder.and(cvEmpty, emailEmpty, phoneEmpty));
			//begin transaction
			Transaction transaction = session.beginTransaction();
			session.createMutationQuery(criteriaUpdate).executeUpdate();
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Candidate> pagingCandidate(int pageNumber, int pageSize) throws HibernateException {
		pageNumber = pageNumber <= 0 ? 1 : pageNumber;
		pageSize = pageSize <= 0 ? 1 : pageSize;
		
		List<Candidate> candidates = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
			
			Root<Candidate> root = criteriaQuery.from(Candidate.class);
			criteriaQuery.select(root);
			
			Query<Candidate> query = session.createQuery(criteriaQuery);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			
			candidates = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return candidates;
	}

}
