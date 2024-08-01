package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Patient;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;
import fa.training.util.LoggerUtil;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

public class PatientDAOImpl implements PatientDAO {
	private static final Logger logger = LoggerUtil.getLogger();

	@Override
	public Patient getPatientByID(int patientId) throws HibernateException {
		if (patientId <= 0) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Patient.class, patientId);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Patient> getAllPatients() throws HibernateException {
		List<Patient> patients = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Patient> query = session.createQuery("FROM Patient", Patient.class);
			patients = query.getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return patients;
	}

	@Override
	public boolean updatePatientByID(int patientId, Patient patient) throws HibernateException, DataNotFoundException {
		if (patientId <= 0 || patient == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Patient existingPatient = getPatientByID(patientId);
			if (existingPatient == null) {
				throw new DataNotFoundException();
			}
			patient.setPatId(patientId);
			Transaction transaction = session.beginTransaction();
			session.merge(patient);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deletePatientByID(int patientId) throws HibernateException, DataNotFoundException {
		if (patientId <= 0) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Patient existingPatient = getPatientByID(patientId);
			if (existingPatient == null) {
				throw new DataNotFoundException();
			}
			Transaction transaction = session.beginTransaction();
			session.remove(existingPatient);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean insertPatient(Patient patient) throws HibernateException, DataAlreadyExistException {
		if (patient == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Patient existingPatient = getPatientByID(patient.getPatId());
			if (existingPatient != null) {
				throw new DataAlreadyExistException();
			}
			Transaction transaction = session.beginTransaction();
			session.merge(patient);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Patient> pagingByProcedure(int pageNumber, int pageSize) throws HibernateException {
		pageNumber = pageNumber <= 0 ? 1 : pageNumber;
		pageSize = pageNumber <= 0 ? 1 : pageSize;
		List<Patient> patients = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			StoredProcedureQuery query = session.createStoredProcedureQuery("PagingPatient", Patient.class);
			query.registerStoredProcedureParameter("pageNumber", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("pageSize", Integer.class, ParameterMode.IN);
			query.setParameter("pageNumber", pageNumber);
			query.setParameter("pageSize", pageSize);
			
			patients = query.getResultList();
			
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return patients;
	}

}
