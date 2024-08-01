package fa.training.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fa.training.entities.NhanVien;
import fa.training.util.HibernateUtil;

public class NhanVienDAO {
	public NhanVien getById(Integer id)  throws Exception {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(NhanVien.class, id);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	public NhanVien insert(NhanVien nhanVien) throws Exception {
		if (nhanVien == null) {
			return null;
		}
		NhanVien newNhanVien =null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			newNhanVien =  session.merge(nhanVien);
			t.commit();
			return newNhanVien;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
