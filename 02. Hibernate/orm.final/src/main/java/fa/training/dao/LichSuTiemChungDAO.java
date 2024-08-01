package fa.training.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.KhachHang;
import fa.training.entities.LichSuTiemChung;
import fa.training.entities.NhanVien;
import fa.training.entities.Vacxin;
import fa.training.util.HibernateUtil;

public class LichSuTiemChungDAO {
	private  KhachHangDAO khachHangDAO = new KhachHangDAO();
	private  NhanVienDAO nhanVienDAO = new NhanVienDAO();
	private  VacxinDAO vacxinDAO = new VacxinDAO();
	
	
	
	public boolean hoanTat2(Integer id) {
		if (id == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			KhachHang khachHang = khachHangDAO.getById(id);
			if (khachHang == null) {
				System.out.println("Khach hang khong hop le");
			}
			Integer chietKhau = 0;
			if (khachHang.getNgaySinh().isAfter(LocalDate.now().minusYears(6))) {
				chietKhau = 10;
			} else if (
					khachHang.getNgaySinh().isBefore(LocalDate.now().minusYears(6))  && 
					khachHang.getNgaySinh().isAfter(LocalDate.now().minusYears(18))
					) {
				chietKhau = 5;
			}
			
			
			Query<LichSuTiemChung> query = session.createQuery("FROM LichSuTiemChung l WHERE l.khachHang = :khachHang AND  l.trangThai = :trangThai", LichSuTiemChung.class);
			query.setParameter("khachHang", khachHang);
			query.setParameter("trangThai", "Cho tiem");
			List<LichSuTiemChung> lstcs = query.getResultList();
			
			if (lstcs.isEmpty()) {
				System.out.println("Khach hang nay khong dang ki tiem");
				return false;
			}
			
			
			Transaction t = session.beginTransaction();
			
			for (LichSuTiemChung lstc : lstcs) {
				lstc.setTrangThai("Da tiem");
				lstc.setTongTien(lstc.getVacxin().getGia() - lstc.getVacxin().getGia() * chietKhau /100);
				lstc.setNgayTiem(LocalDate.now());
				session.merge(lstc);
			}
			
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return true;
	}
	
	
	public boolean hoanTat(Integer id) {
		if (id == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			KhachHang khachHang = khachHangDAO.getById(id);
			if (khachHang == null) {
				System.out.println("Khach hang khong hop le");
			}
			Integer chietKhau = 0;
			if (khachHang.getNgaySinh().isAfter(LocalDate.now().minusYears(6))) {
				chietKhau = 10;
			} else if (
					khachHang.getNgaySinh().isBefore(LocalDate.now().minusYears(6))  && 
					khachHang.getNgaySinh().isAfter(LocalDate.now().minusYears(18))
					) {
				chietKhau = 5;
			}
			
			
			Query<LichSuTiemChung> query = session.createQuery("FROM LichSuTiemChung l WHERE l.khachHang = :khachHang AND  l.trangThai = :trangThai", LichSuTiemChung.class);
			query.setParameter("khachHang", khachHang);
			query.setParameter("trangThai", "Cho tiem");
			List<LichSuTiemChung> lstcs = query.getResultList();
			
			if (lstcs.isEmpty()) {
				System.out.println("Khach hang nay khong dang ki tiem");
				return false;
			}
			
			Integer tongGia = 0;
			for (LichSuTiemChung lstc : lstcs) {
				tongGia += lstc.getVacxin().getGia();
			}
			
			
			Integer tongTien = tongGia - tongGia * chietKhau / 100;
			
			Transaction t = session.beginTransaction();
			
			for (LichSuTiemChung lstc : lstcs) {
				lstc.setTrangThai("Da tiem");
				lstc.setTongTien(tongTien);
				lstc.setNgayTiem(LocalDate.now());
				session.merge(lstc);
			}
			
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return true;
	}
	
	public List<LichSuTiemChung> choTiem() {
		List<LichSuTiemChung> lstcs = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<LichSuTiemChung> query = session.createQuery("FROM LichSuTiemChung l WHERE l.trangThai = :trangThai", LichSuTiemChung.class);
			query.setParameter("trangThai", "Cho tiem");
			
			lstcs = query.getResultList();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return lstcs;
	}
	
	
	public List<KhachHang> choTiemHomNay() {
		List<KhachHang> khachHangs = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<KhachHang> query = session.createQuery("SELECT l.khachHang FROM LichSuTiemChung l WHERE l.trangThai = :trangThai AND l.ngayTiem = :ngayTiem", KhachHang.class);
			query.setParameter("trangThai", "Cho tiem");
			query.setParameter("ngayTiem", LocalDate.now());
			
			khachHangs = query.getResultList();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return khachHangs;
	}
	
	
	
	public  boolean phanCong(Integer khachHangId, Integer nhanVienId, Integer vacxinId) throws Exception {
		KhachHang khachHang = khachHangDAO.getById(khachHangId);
		if (khachHang == null) {
			throw new Exception("Khach hang khong ton tai");
		}
//		if (!khachHang.getLichSuTiemChungs().isEmpty()) {
//			throw new Exception("Khach hang da dang ki tiem chung roi");
//		}
		
		NhanVien nhanVien = nhanVienDAO.getById(nhanVienId);
		if (nhanVien == null) {
			throw new Exception("Nhan vien khong ton tai");
		}
		
		Vacxin vacxin = vacxinDAO.getById(vacxinId);
		if (vacxin == null) {
			throw new Exception("Vacxin khong ton tai");
		}
		boolean duTuoi = khachHang.getNgaySinh().isBefore(LocalDate.now().minusYears(6));
		
		if (duTuoi == false && nhanVien.getNamKinhNghiem() < 3) {
			throw new Exception("Nhan vien chua du 3 nam kinh nghiem");
		}
		LichSuTiemChung lstc = new LichSuTiemChung();
		lstc.setKhachHang(khachHang);
		lstc.setNhanVien(nhanVien);
		lstc.setVacxin(vacxin);
		lstc.setTrangThai("Cho tiem");
		
		return insert(lstc);
		
	}
	
	
	public LichSuTiemChung getById(Integer id)  throws Exception {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(LichSuTiemChung.class, id);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	public boolean insert(LichSuTiemChung lichSuTiemChung) throws Exception {
		if (lichSuTiemChung == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			session.persist(lichSuTiemChung);
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
