package fa.training.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Appointment;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;
import fa.training.util.LoggerUtil;

public class AppointmentDAOImpl implements AppointmentDAO {
	private static final Logger logger = LoggerUtil.getLogger();

	@Override
	public Appointment getAppointmentByID(LocalDate appDate, int patId) throws HibernateException {
		if (appDate == null || patId <= 0) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Appointment> query = session.createQuery("FROM Appointment a WHERE a.appDate = :appDate AND a.patient.patId = :patId", Appointment.class);
			query.setParameter("appDate", appDate);
			query.setParameter("patId", patId);
			return query.getSingleResultOrNull();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Appointment> getAllAppointments() throws HibernateException {
		List<Appointment> appointments = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Appointment> query = session.createQuery("FROM Appointment", Appointment.class);
			appointments =  query.getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return appointments;
	}

	@Override
	public boolean updateAppointmentByID(LocalDate appDate, int patId, Appointment appointment)
			throws HibernateException, DataNotFoundException {
		if (appDate ==  null || patId <= 0) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Appointment existingApp = getAppointmentByID(appDate, patId);
			if (existingApp == null) {
				throw new DataNotFoundException();
			}
			existingApp.setAppTime(appointment.getAppTime());
			existingApp.setAppDuration(appointment.getAppDuration());
			existingApp.setAppReason(appointment.getAppReason());
			//update appointment transaction
			Transaction transaction = session.beginTransaction();
			session.merge(existingApp);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteAppointmentByID(LocalDate appDate, int patId) throws HibernateException, DataNotFoundException {
		if (appDate == null || patId <= 0) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Appointment existingApp = getAppointmentByID(appDate, patId);
			if (existingApp == null) {
				throw new DataNotFoundException();
			}
			//remove appointment transaction
			Transaction transaction = session.beginTransaction();
			session.remove(existingApp);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean insertAppointment(Appointment appointment) throws HibernateException, DataAlreadyExistException {
		if (appointment.getDoctor() == null || appointment.getAppDate() == null ) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Appointment existingApp = getAppointmentByID(appointment.getAppDate(), appointment.getPatient().getPatId());
			if (existingApp != null) {
//				throw new DataAlreadyExistException();
				logger.info("override");
			}
			//insert appointment transaction
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(appointment);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

}
