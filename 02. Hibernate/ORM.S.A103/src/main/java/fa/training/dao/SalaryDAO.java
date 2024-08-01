package fa.training.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.Salary;
import fa.training.util.HibernateUtil;

public class SalaryDAO {
	public boolean insert(Salary salary) throws HibernateException {
		if (salary == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(salary);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
