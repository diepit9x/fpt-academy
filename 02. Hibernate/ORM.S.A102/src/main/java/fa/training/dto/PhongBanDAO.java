package fa.training.dto;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.PhongBan;
import fa.training.util.HibernateUtil;

public class PhongBanDAO {
	public PhongBan insertPhongBan(PhongBan phongBan) {
		if (phongBan == null) {
			return null;
		}
		PhongBan newPhongBan = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			newPhongBan = session.merge(phongBan);
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return newPhongBan;
	}
}
