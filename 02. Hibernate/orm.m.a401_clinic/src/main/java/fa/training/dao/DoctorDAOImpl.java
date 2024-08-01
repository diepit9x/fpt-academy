package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Doctor;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;
import fa.training.util.LoggerUtil;

public class DoctorDAOImpl implements DoctorDAO {
	private static final Logger logger = LoggerUtil.getLogger();
	
	@Override
	public Doctor getDoctorByID(int doctorId) throws HibernateException {
		if (doctorId <= 0) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Doctor.class, doctorId);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Doctor> getAllDoctors() throws HibernateException {
		List<Doctor> doctors = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Doctor> query = session.createQuery("FROM Doctor", Doctor.class);
			doctors = query.getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return doctors;
	}

	@Override
	public boolean updateDoctorByID(int doctorId, Doctor doctor) throws HibernateException, DataNotFoundException {
		if (doctorId <= 0 || doctor == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Doctor existingDoctor = getDoctorByID(doctorId);
			if (existingDoctor == null) {
				throw new DataNotFoundException();
			}
			doctor.setDocNumber(doctorId);
			Transaction transaction = session.beginTransaction();
			session.merge(doctor);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteDoctorByID(int doctorId) throws HibernateException, DataNotFoundException {
		if (doctorId <= 0) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Doctor existingDoctor = getDoctorByID(doctorId);
			if (existingDoctor == null) {
				throw new DataNotFoundException();
			}
			Transaction transaction = session.beginTransaction();
			session.remove(existingDoctor);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean insertDoctor(Doctor doctor) throws HibernateException, DataAlreadyExistException {
		if (doctor	 == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Doctor existingDoctor = getDoctorByID(doctor.getDocNumber());
			if (existingDoctor != null) {
				throw new DataAlreadyExistException();
			}
			Transaction transaction = session.beginTransaction();
			session.merge(doctor);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

}
