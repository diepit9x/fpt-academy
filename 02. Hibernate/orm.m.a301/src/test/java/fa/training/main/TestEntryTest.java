package fa.training.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.dao.EntryTestDAO;
import fa.training.dao.EntryTestDAOImpl;
import fa.training.entities.Candidate;
import fa.training.entities.EntryTest;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public class TestEntryTest {
	private static EntryTestDAO entryTestDAO;
	
	@BeforeClass
	public static void setUp() {
		entryTestDAO = new EntryTestDAOImpl();
	}
	
	@Test
	public void getAll() {
		List<EntryTest> entryTests = entryTestDAO.getAllEntryTests();
		assertFalse(entryTests.isEmpty());
	}
	
	@Test
	public void getById() {
		int entryTestId = 1;
		EntryTest entryTest = entryTestDAO.getEntryTestByID(entryTestId);
		assertNotNull(entryTest);
		
		entryTestId = -2;
		entryTest = entryTestDAO.getEntryTestByID(entryTestId);
		assertNull(entryTest);
	}
	
	@Test
	public void update() throws HibernateException, DataNotFoundException {
		int entryTestId = 1;
		EntryTest entryTest = entryTestDAO.getEntryTestByID(entryTestId);
		assertNotNull(entryTest);
		
		String update = "Update " + LocalDateTime.now();
		entryTest.setRemark(update);
		entryTestDAO.updateEntryTestByID(entryTestId, entryTest);
		
		entryTest = entryTestDAO.getEntryTestByID(entryTestId);
		
		assertEquals(update, entryTest.getRemark());
	}
	
	@Test
	public void insert() throws HibernateException, DataAlreadyExistException {
    	EntryTest entryTest = new EntryTest();
    	entryTest.setEntryTestSkill("skill xxxxxxx");
    	entryTest.setResult("pass");
    	
    	boolean status = entryTestDAO.insertEntryTest(entryTest);
    	assertTrue(status);
    	
    	entryTest.setResult("ok");
    	status = entryTestDAO.insertEntryTest(entryTest);
    	assertFalse(status);
	}
	
	@Test
	public void delete() throws HibernateException, DataNotFoundException {
		int entryTestId = 3;
		boolean status = entryTestDAO.deleteEntryTestByID(entryTestId);
		assertTrue(status);
	}
	
	@Test
	public void findCandidateBySkillAndResult() {
		String skill = "Java";
		String result = "pass";
		LocalDate date = LocalDate.of(2020, 10, 1);
		
		List<Candidate> candidates = entryTestDAO.findCandidateBySkillAndResult(skill, result, date);
		
		assertFalse(candidates.isEmpty());
		
		for (Candidate candidate : candidates) {
			System.out.println(candidate.toString());
		}
		
	}
	
}
