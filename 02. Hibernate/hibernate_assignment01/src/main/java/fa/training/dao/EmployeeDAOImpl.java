package fa.training.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Employee;
import fa.training.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		List<Employee> employees = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			Query<Employee> query = session.createNativeQuery("SELECT * FROM Employee", Employee.class);
			employees = query.list();
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return employees;
	}

	@Override
	public Employee getEmployeeByID(int employeeId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertEmployee(Employee employee) throws SQLException {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.merge(employee);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateEmployeeByID(int employeeId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployeeByID(int employeeId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertAllEmployees(List<Employee> employees) throws SQLException {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			for (Employee employee : employees) {
				session.merge(employee);
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

}
