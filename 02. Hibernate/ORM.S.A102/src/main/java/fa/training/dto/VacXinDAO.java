package fa.training.dto;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.VacXin;
import fa.training.util.HibernateUtil;

public class VacXinDAO {
	public VacXin getVacXinById(Integer idVX) {
		if (idVX == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(VacXin.class, idVX);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public VacXin getVacXinByName(String tenVX) {
		if (tenVX == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<VacXin> query = session.createQuery("FROM VacXin v WHERE v.tenVX = :tenVX", VacXin.class);
			query.setParameter("tenVX", tenVX);
			return query.getSingleResultOrNull();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public VacXin insertVacXin(VacXin vacXin) {
		if (vacXin == null) {
			return null;
		}
		VacXin newVacXin = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			newVacXin = session.merge(vacXin);
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return newVacXin;
	}
}
