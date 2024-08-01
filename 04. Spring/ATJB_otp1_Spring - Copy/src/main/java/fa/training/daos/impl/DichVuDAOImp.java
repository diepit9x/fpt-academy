package fa.training.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fa.training.daos.DichVuDAO;
import fa.training.entities.DichVu;
import fa.training.entities.SuDungDichVu;
import fa.training.models.PagedResult;

@Repository
public class DichVuDAOImp implements DichVuDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public PagedResult<DichVu> findAll(String search, int page, int pageSize) {
		try (Session session = sessionFactory.getCurrentSession()) {
			// count record
			String countHql = "SELECT COUNT(dv) FROM DichVu dv WHERE :search IS NULL OR :search = '' OR dv.maDV LIKE :search OR dv.tenDV LIKE :search";
			Query<Long> countQuery = session.createQuery(countHql, Long.class);
			countQuery.setParameter("search", search != null ? "%" + search + "%" : null);

			Long totalRecords = countQuery.getSingleResult();
			int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

			// get record
			String listHql = "FROM DichVu dv WHERE  :search IS NULL OR :search = '' OR dv.maDV LIKE :search OR dv.tenDV LIKE :search";
			Query<DichVu> listQuery = session.createQuery(listHql, DichVu.class);
			listQuery.setParameter("search", search != null ? "%" + search + "%" : null);
			listQuery.setFirstResult((page - 1) * pageSize);
			listQuery.setMaxResults(pageSize);

			List<DichVu> mays = listQuery.getResultList();

			return new PagedResult<>(page, totalPages, mays);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return new PagedResult<>(page, 0, new ArrayList<>());
	}

	public DichVu findById(String maDV) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(DichVu.class, maDV);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public boolean insert(DichVu DichVu) {
		if (DichVu == null) {
			return false;
		}
		try (Session session = sessionFactory.openSession()) {
			Transaction t = session.beginTransaction();
			session.persist(DichVu);
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public boolean update(DichVu DichVu) {
		try (Session session = sessionFactory.openSession()) {
			DichVu existingDV = findById(DichVu.getMaDV());
			if (existingDV == null) {
				throw new Exception("Mã dịch vụ không tồn tại");
			}
			DichVu.setMaDV(DichVu.getMaDV());

			Transaction t = session.beginTransaction();
			session.merge(DichVu);
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public boolean delete(String maDV) {
		try (Session session = sessionFactory.openSession()) {
			DichVu existingDV = findById(maDV);
			if (existingDV == null) {
				throw new Exception("Mã dịch vụ không tồn tại");
			}

			Transaction t = session.beginTransaction();
			session.remove(existingDV);
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public boolean registerToUse(SuDungDichVu suDungDichVu) {
		if (suDungDichVu == null) {
			return false;
		}
		try (Session session = sessionFactory.openSession()) {
			Transaction t = session.beginTransaction();
			session.persist(suDungDichVu);
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}