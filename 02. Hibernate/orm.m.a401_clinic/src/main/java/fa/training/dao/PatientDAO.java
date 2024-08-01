package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Patient;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface PatientDAO {
	List<Patient> pagingByProcedure(int pageNumber, int pageSize)  throws HibernateException;
	
	Patient getPatientByID(int patientId) throws HibernateException;
	List<Patient> getAllPatients() throws HibernateException;
	boolean updatePatientByID(int patientId, Patient patient) throws HibernateException, DataNotFoundException;
	boolean deletePatientByID(int patientId) throws HibernateException, DataNotFoundException;
	boolean insertPatient(Patient patient) throws HibernateException, DataAlreadyExistException;
}
