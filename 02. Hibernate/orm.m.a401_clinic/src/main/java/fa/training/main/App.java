package fa.training.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import fa.training.dao.AppointmentDAO;
import fa.training.dao.AppointmentDAOImpl;
import fa.training.dao.DoctorDAO;
import fa.training.dao.DoctorDAOImpl;
import fa.training.dao.PatientDAO;
import fa.training.dao.PatientDAOImpl;
import fa.training.entities.Appointment;
import fa.training.entities.Bill;
import fa.training.entities.Doctor;
import fa.training.entities.Patient;
import fa.training.entities.Payment;
import fa.training.exception.DataAlreadyExistException;
import fa.training.util.LoggerUtil;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LoggerUtil.getLogger();
    public static void main( String[] args ) throws HibernateException, DataAlreadyExistException
    {
		DoctorDAO doctorDAO = new DoctorDAOImpl();
		PatientDAO patientDAO = new PatientDAOImpl();
    	
    	Doctor doctor = doctorDAO.getDoctorByID(1);
		Patient patient = patientDAO.getPatientByID(1);
		 
		Appointment appointment = new Appointment();
		appointment.setAppDate(LocalDate.now().plusDays(6));
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		
		Bill bill = new Bill();
		bill.setAppointment(appointment);
		bill.setBillStatus(2);
		
		appointment.setBill(bill);
		
		Payment payment = new Payment();
		payment.setBill(bill);
		payment.setPayAmount(999);
		payment.setPayMethod("cash");
		
		List<Payment> payments = new ArrayList<>();
		payments.add(payment);
		
		bill.setPayments(payments);
		
		AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		
		
		appointmentDAO.insertAppointment(appointment);
		
    }
}
