package fa.training.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Employee;
import fa.training.exception.DataAlreadyExistException;
import fa.training.util.HibernateUtil;
import fa.training.util.HibernateValidator;

public class EmployeeDAO {
	public List<Employee> getAllEmployees() throws HibernateException {
		List<Employee> employees = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
			employees = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return employees;
	}
	
	public Employee getEmployeeById(Integer id) throws HibernateException {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Employee.class, id);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	public Employee insertEmployee(Employee employee) throws HibernateException, DataAlreadyExistException {
		Employee newEmployee = null;
		if (employee == null) {
			return newEmployee;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee existingEmployee = getEmployeeById(employee.getId());
			if (existingEmployee != null) {
				throw new DataAlreadyExistException();
			}
			List<String> errorMessages = HibernateValidator.getViolations(employee);
			if (errorMessages.isEmpty()) {
				Transaction transaction = session.beginTransaction();
				newEmployee =  session.merge(employee);
				transaction.commit();
			} else {
				errorMessages.forEach(System.out::println);
			}
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return newEmployee;
	}
}
