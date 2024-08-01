package fa.training.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Appointment;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface AppointmentDAO {
	Appointment getAppointmentByID(LocalDate appDate, int patId) throws HibernateException;
	List<Appointment> getAllAppointments() throws HibernateException;
	boolean updateAppointmentByID(LocalDate appDate, int patId, Appointment appointment) throws HibernateException, DataNotFoundException;
	boolean deleteAppointmentByID(LocalDate appDate, int patId) throws HibernateException, DataNotFoundException;
	boolean insertAppointment(Appointment appointment) throws HibernateException, DataAlreadyExistException;
}
