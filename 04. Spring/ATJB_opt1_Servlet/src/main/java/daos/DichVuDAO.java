package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.DichVu;
import entities.SuDungDichVu;
import models.PagedResult;
import utils.HibernateUtil;

public class DichVuDAO {
	public PagedResult<DichVu> findAll(String search, int page, int pageSize) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
	
	public boolean delete(String   maDV) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
