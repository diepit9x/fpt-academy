package fa.training.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.DepEmp;
import fa.training.util.HibernateUtil;

public class DepEmpDAO {
	public boolean insert(DepEmp depEmp) throws HibernateException {
		if (depEmp == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(depEmp);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
