package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.KhachHang;
import entities.SuDungDichVuMay;
import models.PagedResult;
import utils.HibernateUtil;

public class KhachHangDAO {
	
	public PagedResult<SuDungDichVuMay> getAllUsingService(int page, int pageSize) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        // count record
	        String countHql = "SELECT COUNT(sdm) FROM SuDungDichVuMay sdm";
	        Query<Long> countQuery = session.createQuery(countHql, Long.class);

	        Long totalRecords = countQuery.getSingleResult();
	        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

	     // Get records
	        String listHql = "SELECT maKH, tenKH, maMay, viTri, trangThai, ngaybatDauSuDung, gioBatDauSuDung, thoiGianSuDung, maDV, ngaySuDung, gioSuDung, soLuong, donGia, id FROM SuDungDichVuMay";
	        Query<Object[]> listQuery = session.createQuery(listHql, Object[].class);
	        listQuery.setFirstResult((page - 1) * pageSize);
	        listQuery.setMaxResults(pageSize);

	        List<Object[]> results = listQuery.list();
	        List<SuDungDichVuMay> sddv = new ArrayList<>();
	        for (Object[] row : results) {
	            SuDungDichVuMay suDungDichVuMay = SuDungDichVuMay.mapToSuDungDichVuMay(row);
	            sddv.add(suDungDichVuMay);
	        }
	        return new PagedResult<>(page, totalPages, sddv);
	    } catch (Exception e) {
	        System.err.println("Error: " + e.getMessage());
	    }
	    return new PagedResult<>(page, 0, new ArrayList<>());
	}
	
	public List<SuDungDichVuMay> getAllUsingService() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<SuDungDichVuMay> query = session.createQuery("FROM SuDungDichVuMay", SuDungDichVuMay.class);
			return query.list();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public PagedResult<KhachHang> findAll(String search, int page, int pageSize) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        // count record
	        String countHql = "SELECT COUNT(kh) FROM KhachHang kh WHERE :search IS NULL OR :search = '' OR kh.maKH LIKE :search OR kh.tenKH LIKE :search OR kh.diaChi LIKE :search OR kh.soDienThoai LIKE :search OR kh.email LIKE :search";
	        Query<Long> countQuery = session.createQuery(countHql, Long.class);
	        countQuery.setParameter("search", search != null ? "%" + search + "%" : null);

	        Long totalRecords = countQuery.getSingleResult();
	        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

	        // get record
	        String listHql = "FROM KhachHang kh WHERE :search IS NULL OR :search = '' OR kh.maKH LIKE :search OR kh.tenKH LIKE :search OR kh.diaChi LIKE :search OR kh.soDienThoai LIKE :search OR kh.email LIKE :search";
	        Query<KhachHang> listQuery = session.createQuery(listHql, KhachHang.class);
	        listQuery.setParameter("search", search != null ? "%" + search + "%" : null);
	        listQuery.setFirstResult((page - 1) * pageSize);
	        listQuery.setMaxResults(pageSize);

	        List<KhachHang> mays = listQuery.getResultList();

	        return new PagedResult<>(page, totalPages, mays);
	    } catch (Exception e) {
	        System.err.println("Error: " + e.getMessage());
	    }
	    return new PagedResult<>(page, 0, new ArrayList<>());
	}
	
	public KhachHang findById(String maKH) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(KhachHang.class, maKH);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public boolean insert(KhachHang khachHang) {
		if (khachHang == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			session.persist(khachHang);
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean update(KhachHang khachHang) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			KhachHang existingKH = findById(khachHang.getMaKH());
			if (existingKH == null) {
				throw new Exception("Mã khách hàng không tồn tại");
			}
			khachHang.setMaKH(khachHang.getMaKH());
			
			Transaction t = session.beginTransaction();
			session.merge(khachHang);
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean delete(String   maKH) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			KhachHang existingKH = findById(maKH);
			if (existingKH == null) {
				throw new Exception("Mã khách hàng không tồn tại");
			}
			
			Transaction t = session.beginTransaction();
			session.remove(existingKH);
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

}
