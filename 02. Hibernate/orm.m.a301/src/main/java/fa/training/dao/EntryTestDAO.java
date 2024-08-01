package fa.training.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Candidate;
import fa.training.entities.EntryTest;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface EntryTestDAO {
	List<Candidate> findCandidateBySkillAndResult(String entryTestSkill, String result, LocalDate date) throws HibernateException; 
	EntryTest getEntryTestByID(int entryTestId) throws HibernateException;
	List<EntryTest> getAllEntryTests() throws HibernateException;
	boolean updateEntryTestByID(int entryTestId, EntryTest entryTest) throws HibernateException, DataNotFoundException;
	boolean deleteEntryTestByID(int entryTestId) throws HibernateException, DataNotFoundException;
	boolean insertEntryTest(EntryTest entryTest) throws HibernateException, DataAlreadyExistException;
}
