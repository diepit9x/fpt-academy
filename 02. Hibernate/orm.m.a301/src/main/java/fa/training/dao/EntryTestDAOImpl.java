package fa.training.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Candidate;
import fa.training.entities.EntryTest;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;
import fa.training.util.HibernateValidator;

public class EntryTestDAOImpl implements EntryTestDAO {

	@Override
	public EntryTest getEntryTestByID(int entryTestId) throws HibernateException {
		if (entryTestId <= 0) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(EntryTest.class, entryTestId);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<EntryTest> getAllEntryTests() throws HibernateException {
		List<EntryTest> entryTests = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<EntryTest> query = session.createQuery("FROM EntryTest", EntryTest.class);
			entryTests = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return entryTests;
	}

	@Override
	public boolean updateEntryTestByID(int entryTestId, EntryTest entryTest)
			throws HibernateException, DataNotFoundException {
		if (entryTestId <= 0 || entryTest == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			EntryTest existingEntryTest = getEntryTestByID(entryTestId);
			if (existingEntryTest == null) {
				throw new DataNotFoundException();
			}
			List<String> errrorMessages = HibernateValidator.getViolations(entryTest);
			if (errrorMessages.isEmpty()) {
				entryTest.setTestId(existingEntryTest.getTestId());
				session.merge(entryTest);
			} else {
				errrorMessages.forEach(System.err::println);
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
	public boolean deleteEntryTestByID(int entryTestId) throws HibernateException, DataNotFoundException {
		if (entryTestId <= 0) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			EntryTest existingEntryTest = getEntryTestByID(entryTestId);
			if (existingEntryTest == null) {
				throw new DataNotFoundException();
			}
			session.remove(existingEntryTest);
			
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean insertEntryTest(EntryTest entryTest) throws HibernateException, DataAlreadyExistException {
		if (entryTest == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			EntryTest existingEntryTest = getEntryTestByID(entryTest.getTestId());
			if (existingEntryTest != null) {
				throw new DataAlreadyExistException();
			}
			List<String> errrorMessages = HibernateValidator.getViolations(entryTest);
			if (errrorMessages.isEmpty()) {
				session.merge(entryTest);
			} else {
				errrorMessages.forEach(System.err::println);
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
	public List<Candidate> findCandidateBySkillAndResult(String entryTestSkill, String result, LocalDate date)
			throws HibernateException {
		List<Candidate> candidates = new ArrayList<>();
		if (entryTestSkill == null || result == null || date == null) {
			return candidates;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Candidate> query = session.createQuery("SELECT DISTINCT et.candidate FROM EntryTest et "
					+ "WHERE et.candidate IS NOT NULL "
					+ "AND et.entryTestSkill = :entryTestSkill "
					+ "AND et.result = :result "
					+ "AND et.date = :date", Candidate.class);
			query.setParameter("entryTestSkill", entryTestSkill);
			query.setParameter("result", result);
			query.setParameter("date", date);
			candidates =  query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return candidates;
	}

}
