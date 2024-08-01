package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Doctor;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface DoctorDAO {
	Doctor getDoctorByID(int doctorId) throws HibernateException;
	List<Doctor> getAllDoctors() throws HibernateException;
	boolean updateDoctorByID(int doctorId, Doctor doctor) throws HibernateException, DataNotFoundException;
	boolean deleteDoctorByID(int doctorId) throws HibernateException, DataNotFoundException;
	boolean insertDoctor(Doctor doctor) throws HibernateException, DataAlreadyExistException;
}
