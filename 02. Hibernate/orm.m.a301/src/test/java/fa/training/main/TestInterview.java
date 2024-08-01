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

import fa.training.dao.CandidateDAO;
import fa.training.dao.CandidateDAOImpl;
import fa.training.dao.InterviewDAO;
import fa.training.dao.InterviewDAOImpl;
import fa.training.entities.Candidate;
import fa.training.entities.Interview;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public class TestInterview {
	private static InterviewDAO interviewDAO;
	
	@BeforeClass
	public static void setUp() {
		interviewDAO = new InterviewDAOImpl();
	}
	
	@Test
	public void getAll() {
		List<Interview> interviews = interviewDAO.getAllInterviews();
		assertFalse(interviews.isEmpty());
	}
	
	@Test
	public void getById() {
		int interviewId = 1;
		Interview interview = interviewDAO.getInterviewByID(interviewId);
		assertNotNull(interview);
		
		interviewId = 200;
		interview = interviewDAO.getInterviewByID(interviewId);
		assertNull(interview);
	}
	
	@Test
	public void update() throws HibernateException, DataNotFoundException {
		int interviewId = 1;
		Interview interview = interviewDAO.getInterviewByID(interviewId);
		assertNotNull(interview);
		
		String update = "Update " + LocalDateTime.now();
		interview.setComments(update);
		interviewDAO.updateInterviewByID(interviewId, interview);
		
		interview = interviewDAO.getInterviewByID(interviewId);
		
		assertEquals(update, interview.getComments());
	}
	
	@Test
	public void insert() throws HibernateException, DataAlreadyExistException {
		CandidateDAO candidateDAO = new CandidateDAOImpl();
		Candidate candidate = candidateDAO.getCandidateByID(1);
		assertNotNull(candidate);
		
		Interview interview = new Interview();
		interview.setCandidate(candidate);
		
		boolean status = interviewDAO.insertInterview(interview);
		assertTrue(status);
	}
	
	
	@Test
	public void delete() throws HibernateException, DataNotFoundException {
		int interviewId = 2;
		boolean status = interviewDAO.deleteInterviewByID(interviewId);
		assertTrue(status);
		
		interviewId = 30;
		status = interviewDAO.deleteInterviewByID(interviewId);
		assertFalse(status);
	}
	
	@Test
	public void findByInterviewResultAndDate() {
		String result = "pass";
		LocalDate date = LocalDate.of(2020, 10, 15);
		
		List<Candidate> candidates = interviewDAO.findByInterviewResultAndDate(result, date);
		
		assertFalse(candidates.isEmpty());
		
		candidates.forEach(System.out::println);
		
	}
}
