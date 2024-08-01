package fa.training.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.Vacxin;
import fa.training.util.HibernateUtil;

public class VacxinDAO {
	public Vacxin getById(Integer id)  throws Exception {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Vacxin.class, id);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	public Vacxin insert(Vacxin vacxin) throws Exception {
		if (vacxin == null) {
			return null;
		}
		Vacxin newVacxin =null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			newVacxin =  session.merge(vacxin);
			t.commit();
			return newVacxin;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
