package fa.training.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.dao.PatientDAO;
import fa.training.dao.PatientDAOImpl;
import fa.training.entities.Patient;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public class TestPatientDAO {
	private static PatientDAO patientDAO;
	
	@BeforeClass
	public static void setUp() {
		patientDAO = new PatientDAOImpl();
	}
	
	@Test
	public void getAll() {
		List<Patient> patients = patientDAO.getAllPatients();
		assertFalse(patients.isEmpty());
		patients.forEach(p -> System.out.println(p.toString()));
	}
	
	@Test
	public void getById() {
		int patientId = 1;
		Patient patient = patientDAO.getPatientByID(patientId);
		assertNotNull(patient);
	}
	
	@Test
	public void update() throws HibernateException, DataNotFoundException {
		int patientId = 1;
		Patient patient = patientDAO.getPatientByID(patientId);
		
		String update = "update " + LocalDateTime.now();
		patient.setPatState(update);
		boolean status = patientDAO.updatePatientByID(patientId, patient);
		assertTrue(status);
		
		patient = patientDAO.getPatientByID(patientId);
		assertEquals(update, patient.getPatState());
	}
	
	@Test
	public void insert() throws HibernateException, DataAlreadyExistException {
		Patient newPatient = new Patient();
		newPatient.setPatFirstname("pat fisrt4");
		newPatient.setPatLastname("pat last4");
		newPatient.setPatAddress("address4");
		newPatient.setPatCity("city4");
		newPatient.setPatState("state14");
		
		boolean status = patientDAO.insertPatient(newPatient);
		assertTrue(status);
	}
	
	@Test
	public void delete() throws HibernateException, DataNotFoundException {
		int patientId = 3;
		boolean status = patientDAO.deletePatientByID(patientId);
		assertTrue(status);
	}
	
	@Test
	public void pagingPatient() {
		int pageNumber = 1;
		int pageSize = 2;
		
		List<Patient> patients = patientDAO.pagingByProcedure(pageNumber, pageSize);
		assertFalse(patients.isEmpty());
		patients.forEach(p -> System.out.println(p.toString()));
		
	}
}
