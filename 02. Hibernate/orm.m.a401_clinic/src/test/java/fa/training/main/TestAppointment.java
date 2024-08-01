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

import fa.training.dao.AppointmentDAO;
import fa.training.dao.AppointmentDAOImpl;
import fa.training.dao.BillDAO;
import fa.training.dao.BillDAOImpl;
import fa.training.dao.DoctorDAO;
import fa.training.dao.DoctorDAOImpl;
import fa.training.dao.PatientDAO;
import fa.training.dao.PatientDAOImpl;
import fa.training.entities.Appointment;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public class TestAppointment {
	private static AppointmentDAO appointmentDAO;
	
	@BeforeClass
	public static void setUp() {
		appointmentDAO = new AppointmentDAOImpl();
	}
	
	@Test
	public void getAll() {
		List<Appointment> appointments = appointmentDAO.getAllAppointments();
		assertFalse(appointments.isEmpty());		
		appointments.forEach(app -> System.out.println(app.toString()));
	}
	
	@Test
	public void getById() {
		LocalDate date = LocalDate.of(2020, 1, 3);
		int patId = 4;
		Appointment appointment = appointmentDAO.getAppointmentByID(date, patId);
		assertNotNull(appointment);
		System.out.println(appointment.getPatient().getAppointments().toString());
		System.out.println(appointment.toString());
	}
	
	@Test
	public void update() throws HibernateException, DataNotFoundException {
		LocalDate date = LocalDate.of(2020, 1, 1);
		int patId = 1;
		Appointment appointment = appointmentDAO.getAppointmentByID(date, patId);
		assertNotNull(appointment);
		
		String update = "update " + LocalDateTime.now();
		appointment.setAppReason(update);
		
		boolean status = appointmentDAO.updateAppointmentByID(date, patId, appointment);
		assertTrue(status);
		
		appointment = appointmentDAO.getAppointmentByID(date, patId);
		assertEquals(update, appointment.getAppReason());
	}
	
	@Test
	public void insert() throws HibernateException, DataAlreadyExistException {
		DoctorDAO doctorDAO = new DoctorDAOImpl();
		PatientDAO patientDAO = new PatientDAOImpl();
		BillDAO billDAO = new BillDAOImpl();
		
		Appointment newApp = new Appointment();
		newApp.setAppDate(LocalDate.of(2020, 1, 3));
		newApp.setPatient(patientDAO.getPatientByID(4));
		newApp.setDoctor(doctorDAO.getDoctorByID(3));
		newApp.setBill(billDAO.getBillByID(1));
		newApp.setAppDuration(100);
		
		boolean status = appointmentDAO.insertAppointment(newApp);
		assertTrue(status);
	}
	
	@Test
	public void delete() throws HibernateException, DataNotFoundException {
		LocalDate date = LocalDate.now();
		int patId = 1;
		boolean status = appointmentDAO.deleteAppointmentByID(date, patId);
		assertTrue(status);
	}
}

