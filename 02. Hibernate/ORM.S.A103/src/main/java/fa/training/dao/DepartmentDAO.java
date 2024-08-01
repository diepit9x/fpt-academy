package fa.training.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.Department;
import fa.training.util.HibernateUtil;

public class DepartmentDAO {
	public boolean insert(Department department) throws HibernateException {
		if (department == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(department);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
