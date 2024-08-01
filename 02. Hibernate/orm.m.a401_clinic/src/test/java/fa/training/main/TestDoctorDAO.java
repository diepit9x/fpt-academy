package fa.training.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.dao.DoctorDAO;
import fa.training.dao.DoctorDAOImpl;
import fa.training.dao.PatientDAO;
import fa.training.dao.PatientDAOImpl;
import fa.training.entities.Appointment;
import fa.training.entities.Doctor;
import fa.training.entities.Patient;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public class TestDoctorDAO {
	private static DoctorDAO doctorDAO;
	
	@BeforeClass
	public static void setUp() {
		doctorDAO = new DoctorDAOImpl();
	}
	
	@Test
	public void getAll() {
		List<Doctor> doctors = doctorDAO.getAllDoctors();
		
		assertFalse(doctors.isEmpty());
		
		doctors.forEach(d -> System.out.println(d.toString()));
	}
	
	@Test
	public void getById() {
		int doctorId = 1;
		Doctor doctor = doctorDAO.getDoctorByID(doctorId);
		assertNotNull(doctor);
	}
	
	@Test
	public void update() throws HibernateException, DataNotFoundException {
		int doctorId = 1;
		Doctor doctor = doctorDAO.getDoctorByID(doctorId);
		assertNotNull(doctor);
		
		String update = "update " + LocalDateTime.now();
		doctor.setDocLastname(update);
		
		boolean status = doctorDAO.updateDoctorByID(doctorId, doctor);
		assertTrue(status);
		
		doctor = doctorDAO.getDoctorByID(doctorId);
		assertEquals(update, doctor.getDocLastname());
	}
	
	@Test
	public void insert() throws HibernateException, DataAlreadyExistException {
		Doctor newDoctor = new Doctor();
		newDoctor.setDocFirstname("new first 2");
		newDoctor.setDocLastname("new last 2");
		
		boolean status = doctorDAO.insertDoctor(newDoctor);
		assertTrue(status);
	}
	
	@Test
	public void delete() throws HibernateException, DataNotFoundException {
		int doctorId  = 2;
		boolean status = doctorDAO.deleteDoctorByID(doctorId);
		assertTrue(status);
	}
	
	@Test
	public void createDoctorWith3Appointment() throws HibernateException, DataAlreadyExistException {
		PatientDAO patientDAO = new PatientDAOImpl();
		Patient patient = patientDAO.getPatientByID(1);
		
		Appointment app1 = new Appointment();
		app1.setPatient(patient);
		app1.setAppDate(LocalDate.of(2022, 11, 1));
		app1.setAppTime("8:00 AM");
		
		Appointment app2 = new Appointment();
		app2.setPatient(patient);
		app2.setAppDate(LocalDate.of(2022, 11, 2));
		app2.setAppTime("9:00 AM");
		
		Appointment app3 = new Appointment();
		app3.setPatient(patient);
		app3.setAppDate(LocalDate.of(2022, 11, 4));
		app3.setAppTime("10:00 AM");
		
		List<Appointment> apps = new ArrayList<>();
		apps.add(app1);
		apps.add(app2);
		apps.add(app3);
		
		
		Doctor newDoctor = new Doctor();
		newDoctor.setDocFirstname("doctor fn");
		newDoctor.setDocLastname("doctor ln");
		newDoctor.setAppointments(apps);
		
		boolean status = doctorDAO.insertDoctor(newDoctor);
		assertTrue(status);
		
	}
}
