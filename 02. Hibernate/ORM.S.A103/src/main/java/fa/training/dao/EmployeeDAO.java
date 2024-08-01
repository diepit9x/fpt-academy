package fa.training.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.Employee;
import fa.training.util.HibernateUtil;

public class EmployeeDAO {
	public boolean insert(Employee employee) throws HibernateException {
		if (employee == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(employee);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
