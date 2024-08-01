package fa.training.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.NhanVien;
import fa.training.entities.PhongBan;
import fa.training.entities.ThongTinTiemChung;
import fa.training.entities.VacXin;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;

public class ThongTinTiemChungDAO {
	public void thongKe() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			Query<PhongBan> queryPB = session.createQuery("FROM PhongBan", PhongBan.class);
			List<PhongBan> phongBans = queryPB.getResultList();
			
			for (PhongBan phongBan : phongBans) {
				Query<NhanVien> queryNV = session.createQuery("FROM NhanVien nv WHERE nv.phongBan = :phongBan", NhanVien.class);
				queryNV.setParameter("phongBan", phongBan);
				List<NhanVien> nhanViens = queryNV.getResultList();
				
				String hqlChuaTiem = "SELECT COUNT(*) FROM ThongTinTiemChung t WHERE t.nhanVien IN (:nhanViens) "
						+ "AND t.vacXin1 IS NULL AND t.vacXin2 IS NULL";
				Query queryChuaTiem = session.createQuery(hqlChuaTiem);
				queryChuaTiem.setParameter("nhanViens", nhanViens);
				Long countChuaTiem = (Long) queryChuaTiem.uniqueResult();
				
				String hqlTiem1Mui = "SELECT COUNT(*) FROM ThongTinTiemChung t WHERE t.nhanVien IN (:nhanViens) "
						+ "AND t.vacXin1 IS NOT NULL AND t.vacXin2 IS NULL";
				Query queryTiem1Mui = session.createQuery(hqlTiem1Mui);
				queryTiem1Mui.setParameter("nhanViens", nhanViens);
				Long countTiem1Mui = (Long) queryTiem1Mui.uniqueResult();
				
				
				
				System.out.println(phongBan.getTenPB());
				System.out.println("So NV: " + nhanViens.size());
				System.out.println("Chua tiem: " + countChuaTiem);
				System.out.println("Tiem 1 mui: " + countTiem1Mui);
				System.out.println("Tiem 2 mui: " + (nhanViens.size() - countChuaTiem - countTiem1Mui));
				 
			}
			
			
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		
		
		
	}
	
	
	public List<ThongTinTiemChung> getByVacXin(VacXin vacXin){
		List<ThongTinTiemChung> tttcs = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM ThongTinTiemChung t WHERE t.vacXin1 = :vacXin OR t.vacXin2 = :vacXin";
			Query<ThongTinTiemChung> query = session.createQuery(hql, ThongTinTiemChung.class);
			query.setParameter("vacXin", vacXin);
			tttcs = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return tttcs;
	}
	
	
	public ThongTinTiemChung getTTTCById(NhanVien nhanVien) {
		if (nhanVien == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<ThongTinTiemChung> query = session.createQuery("FROM ThongTinTiemChung t WHERE t.nhanVien = :nhanVien", ThongTinTiemChung.class);
			query.setParameter("nhanVien", nhanVien);
			return query.getSingleResultOrNull();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public List<ThongTinTiemChung> getAllTTTC(){
		List<ThongTinTiemChung> tttcs = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<ThongTinTiemChung> query = session.createQuery("FROM ThongTinTiemChung", ThongTinTiemChung.class);
			tttcs = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return tttcs;
	}
	
	public ThongTinTiemChung insertThongTinTiemChung(ThongTinTiemChung thongTinTiemChung) throws Exception {
		if (thongTinTiemChung == null) {
			return null;
		}
		ThongTinTiemChung newThongTinTiemChung = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			newThongTinTiemChung = session.merge(thongTinTiemChung);
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return newThongTinTiemChung;
	}

	public boolean tttcMacDinh() throws Exception  {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			String hql = "SELECT nv FROM NhanVien nv LEFT JOIN nv.thongTinTiemChung tt WHERE tt IS NULL";
			Query<NhanVien> query = session.createQuery(hql, NhanVien.class);
			List<NhanVien> nhanViens = query.getResultList();

			if (!nhanViens.isEmpty()) {
				ThongTinTiemChung tttc;
				for (NhanVien nhanVien : nhanViens) {
					tttc = new ThongTinTiemChung();
					tttc.setCoTheTiem(true);
					tttc.setNhanVien(nhanVien);
					session.save(tttc);
				}
			}

			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateTTTC(ThongTinTiemChung tttc) throws Exception  {
		if (tttc == null || tttc.getNhanVien() == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			ThongTinTiemChung existingTTTC = getTTTCById(tttc.getNhanVien());
			if (existingTTTC == null) {
				throw new DataNotFoundException();
			}
			Transaction transaction = session.beginTransaction();
			session.update(tttc);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}
