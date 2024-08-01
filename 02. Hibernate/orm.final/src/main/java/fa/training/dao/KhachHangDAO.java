package fa.training.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.KhachHang;
import fa.training.util.HibernateUtil;

public class KhachHangDAO {
	
	public KhachHang getById(Integer id)  throws Exception {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(KhachHang.class, id);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	public KhachHang insert(KhachHang khachHang) throws Exception {
		if (khachHang == null) {
			return null;
		}
		KhachHang newKhachHang =null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			newKhachHang =  session.merge(khachHang);
			t.commit();
			return newKhachHang;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
