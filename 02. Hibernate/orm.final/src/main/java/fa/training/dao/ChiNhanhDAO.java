package fa.training.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.ChiNhanh;
import fa.training.util.HibernateUtil;

public class ChiNhanhDAO {
	public ChiNhanh getById(Integer id)  throws Exception {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(ChiNhanh.class, id);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public ChiNhanh insert(ChiNhanh chiNhanh) throws Exception {
		if (chiNhanh == null) {
			return null;
		}
		ChiNhanh newChiNhanh =null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			newChiNhanh =  session.merge(chiNhanh);
			t.commit();
			return newChiNhanh;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
