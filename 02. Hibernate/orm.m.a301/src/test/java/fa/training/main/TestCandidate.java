package fa.training.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.dao.CandidateDAO;
import fa.training.dao.CandidateDAOImpl;
import fa.training.entities.Candidate;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public class TestCandidate {
	private static CandidateDAO candidateDAO;
	
	@BeforeClass
	public static void setUp() {
		candidateDAO = new CandidateDAOImpl();
	}
	
	@Test
	public void getAll() {
		List<Candidate> candidates = candidateDAO.getAllCandidates();
		assertNotNull(candidates);
	}
	
	@Test
	public void getById() {
		int candidateId = 1;
		Candidate candidate = candidateDAO.getCandidateByID(candidateId);
		assertNotNull(candidate);
		if (candidate != null) {
			assertEquals(candidateId, candidate.getCandidateId());
		}
	}
	
	@Test
	public void insert() throws HibernateException, DataAlreadyExistException {
		Candidate candidate = new Candidate();
		candidate.setFullName("fullname");
		candidate.setDateOfBirth(LocalDate.now());
		candidate.setGender(1);
		candidate.setGraduationYear(LocalDate.now());
		candidate.setPhone("0987654328");
		candidate.setEmail("email@sefd.com1");
		candidate.setLevel(5);
		
		boolean status = candidateDAO.insertCandidate(candidate);
		assertTrue(status);
		
	}
	
	@Test
	public void update() throws HibernateException, DataNotFoundException {
		int candidateId = 1;
		Candidate candidate = candidateDAO.getCandidateByID(candidateId);
		assertNotNull(candidate);
		
		String update = "update test " + LocalDateTime.now();
		candidate.setRemark(update);
		boolean status = candidateDAO.updateCandidateByID(candidateId, candidate);
		assertTrue(status);
		
		candidate = candidateDAO.getCandidateByID(candidateId);
		
		assertEquals(update, candidate.getRemark());
		
	}
	
	@Test
	public void delete() throws HibernateException, DataNotFoundException {
		boolean status = candidateDAO.deleteCandidateByID(6);
		assertTrue(status);
	}
	
	@Test
	public void findBySkillAndLevel() {
		String skill = "Angular";
		int level = 5;
		
		List<Candidate> candidates = candidateDAO.findBySkillAndLevel(skill, level);
		assertFalse(candidates.isEmpty());
		
		candidates.forEach(System.out::println);
		
	}
	
	@Test
	public void findBySkillAndLanguage() {
		String skill = "Python/ML";
		String language = "Japanese";
		List<Candidate> candidates = candidateDAO.findBySkillAndLanguage(skill, language);
		
		assertFalse(candidates.isEmpty());
		
		candidates.forEach(System.out::println);
	}
	
	@Test
	public void updateRemark() {
		boolean status = candidateDAO.updateRemark();
		assertTrue(status);
	}
	
	@Test
	public void pagingCandidate() {
	 	int pageNumber = 1;
		int pageSize = 2;
		List<Candidate> candidates = candidateDAO.pagingCandidate(pageNumber, pageSize);
		assertFalse(candidates.isEmpty());
		
		candidates.forEach(t -> System.out.println(t.toString()));
	}
}
