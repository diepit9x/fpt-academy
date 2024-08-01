package fa.training.dto;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.NhanVien;
import fa.training.util.HibernateUtil;

public class NhanVienDAO {
	public NhanVien getNhanVienById(Integer idNv) {
		if (idNv == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(NhanVien.class, idNv);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public NhanVien insertNhanVien(NhanVien nhanVien) {
		if (nhanVien == null) {
			return null;
		}
		NhanVien newNhanVien = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			newNhanVien = session.merge(nhanVien);
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return newNhanVien;
	}
}
