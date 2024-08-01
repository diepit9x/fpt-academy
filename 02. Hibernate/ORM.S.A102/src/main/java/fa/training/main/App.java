package fa.training.main;

import java.awt.Toolkit;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import fa.training.dto.NhanVienDAO;
import fa.training.dto.PhongBanDAO;
import fa.training.dto.ThongTinTiemChungDAO;
import fa.training.dto.VacXinDAO;
import fa.training.entities.NhanVien;
import fa.training.entities.PhongBan;
import fa.training.entities.ThongTinTiemChung;
import fa.training.entities.VacXin;

public class App {
	private static PhongBanDAO phongBanDAO = new PhongBanDAO();
	private static NhanVienDAO nhanVienDAO = new NhanVienDAO();
	private static VacXinDAO vacXinDAO = new VacXinDAO();
	private static ThongTinTiemChungDAO tttcDAO = new ThongTinTiemChungDAO();

	public static void main(String[] args) {
		Scanner scanner = null;
		App app = new App();
		String choice;
		boolean exit = false;
		
		try {
			scanner = new Scanner(System.in);
			do {
				app.menu();
				choice = scanner.nextLine();
				switch (choice) {
				case "1": {
					try {
						app.capNhatTiemChung(scanner);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				}
				case "2": {
					try {
						app.capNhatMui1(scanner);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				}
				case "3": {
					try {
						app.capNhatMui2(scanner);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				}
				case "4": {
					app.timTheoVacXin(scanner);
					break;
				}
				case "5": {
					tttcDAO.thongKe();
					break;
				}
				case "6": {
					exit = true;
					break;
				}
				default:
					System.err.println("Lua chon khong hop le");
					continue;
				}
				if (exit) {
					break;
				}
				
			} while (true);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	public void timTheoVacXin(Scanner scanner) {
		String tenVX;
		VacXin vacXin;
		
		System.out.print("Nhap ten vacxin: ");
		do {
			tenVX = scanner.nextLine();
			vacXin = vacXinDAO.getVacXinByName(tenVX);
			if (vacXin == null) {
				System.out.print("Ten vacxin khong ton tai. Nhap lai: ");
				continue;
			}
			break;
		} while (true);
		
		List<ThongTinTiemChung> tttcs = tttcDAO.getByVacXin(vacXin);
		
		if (tttcs.isEmpty()) {
			System.out.println("Khong co nhan vien tiem loai vac xin nay");
		} else {
			tttcs.forEach(t -> System.out.println(t.getNhanVien().toString()));
		}
	}
	
	
	public void capNhatMui2(Scanner scanner) throws Exception {
		Integer idNv;
		LocalDate ngaytiem;
		String tenVX;
		NhanVien nhanVien;
		ThongTinTiemChung tttc;
		VacXin vacXin;

		System.out.println("-------Cap nhat mui 2-------");
		System.out.print("Nhap ID_NV: ");

		do {
			try {
				idNv = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Nhap ID_NV khong hop le. Nhap lai: ");
				continue;
			}
			nhanVien = nhanVienDAO.getNhanVienById(idNv);
			if (nhanVien == null) {
				System.err.print("Nhan vien khong ton tai. Nhap lai: ");
				continue;
			}
			tttc = tttcDAO.getTTTCById(nhanVien);
			if (tttc == null) {
				System.err.print("Nhan vien khong ton tai. Nhap lai: ");
				continue;
			}
			if (!tttc.getCoTheTiem()) {
				System.out.print("Nhan vien nay khong duoc tiem. Nhap lai: ");
				continue;
			}
			if (tttc.getVacXin1() == null || tttc.getNgayTiem1() == null) {
				System.out.print("Nhan vien nay chua tiem mui 1. Nhap lai: ");
				continue;
			}
			if (tttc.getVacXin2() != null) {
				System.out.print("Nhan vien nay da tiem mui 2. Nhap lai: ");
				continue;
			}
			break;
		} while (true);
		
		System.out.print("Nhap ten vacxin: ");
		do {
			tenVX = scanner.nextLine();
			vacXin = vacXinDAO.getVacXinByName(tenVX);
			if (vacXin == null) {
				System.out.print("Ten vacxin khong ton tai. Nhap lai: ");
				continue;
			}
			break;
		} while (true);
		
		System.out.print("Nhap ngay tiem (yyyy-MM-dd): ");
		do {
			try {
				ngaytiem = LocalDate.parse(scanner.nextLine());
				if (tttc.getNgayTiem1().isAfter(ngaytiem)) {
					System.err.print("Ngay tiem mui 2 phai sau mui 1. Nhap lai:");
					continue;
				}
				break;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.print("Ngay khong hop le. Nhap lai: ");
			}
		} while (true);
		
		tttc.setVacXin2(vacXin);
		tttc.setNgayTiem2(ngaytiem);
		
		boolean status = tttcDAO.updateTTTC(tttc);
		if (status) {
			System.out.println("Cap nhat thanh cong");
		} else {
			System.err.print("Cap nhat that bai");
		}
	}
	
	
	public void capNhatMui1(Scanner scanner) throws Exception {
		Integer idNv;
		LocalDate ngaytiem;
		String tenVX;
		NhanVien nhanVien;
		ThongTinTiemChung tttc;
		VacXin vacXin;

		System.out.println("-------Cap nhat mui 1-------");
		System.out.print("Nhap ID_NV: ");

		do {
			try {
				idNv = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Nhap ID_NV khong hop le. Nhap lai: ");
				continue;
			}
			nhanVien = nhanVienDAO.getNhanVienById(idNv);
			if (nhanVien == null) {
				System.err.print("Nhan vien khong ton tai. Nhap lai: ");
				continue;
			}
			tttc = tttcDAO.getTTTCById(nhanVien);
			if (tttc == null) {
				System.err.print("Nhan vien khong ton tai. Nhap lai: ");
				continue;
			}
			if (!tttc.getCoTheTiem()) {
				System.out.print("Nhan vien nay khong duoc tiem. Nhap lai: ");
				continue;
			}
			if (tttc.getVacXin1() != null) {
				System.out.print("Nhan vien nay da tiem mui 1. Nhap lai: ");
				continue;
			}
			break;
		} while (true);
		
		System.out.print("Nhap ten vacxin: ");
		do {
			tenVX = scanner.nextLine();
			vacXin = vacXinDAO.getVacXinByName(tenVX);
			if (vacXin == null) {
				System.out.print("Ten vacxin khong ton tai. Nhap lai: ");
				continue;
			}
			break;
		} while (true);
		
		System.out.print("Nhap ngay tiem (yyyy-MM-dd): ");
		do {
			try {
				ngaytiem = LocalDate.parse(scanner.nextLine());
				break;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Ngay khong hop le. Nhap lai: ");
			}
		} while (true);
		
		tttc.setVacXin1(vacXin);
		tttc.setNgayTiem1(ngaytiem);
		
		boolean status = tttcDAO.updateTTTC(tttc);
		if (status) {
			System.out.println("Cap nhat thanh cong");
		} else {
			System.err.print("Cap nhat that bai");
		}
	}
	
	
	public void capNhatTiemChung(Scanner scanner) throws Exception {
		Integer idNv;
		NhanVien nhanVien;
		ThongTinTiemChung tttc;

		System.out.println("-------Cap nhat tiem chung-------");
		System.out.print("Nhap ID_NV: ");

		do {
			try {
				idNv = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Nhap ID_NV khong hop le. Nhap lai: ");
				continue;
			}
			nhanVien = nhanVienDAO.getNhanVienById(idNv);
			if (nhanVien == null) {
				System.err.print("Nhan vien khong ton tai. Nhap lai: ");
				continue;
			}
			tttc = tttcDAO.getTTTCById(nhanVien);
			if (tttc == null) {
				System.err.print("Nhan vien khong ton tai2. Nhap lai: ");
				continue;
			}
			break;
		} while (true);
		
		tttc.setCoTheTiem(false);
		boolean status = tttcDAO.updateTTTC(tttc);
		if (status) {
			System.out.println("Cap nhat thanh cong");
		} else {
			System.err.print("Cap nhat that bai");
		}
	}

	public void menu() {
		System.out.println("======MENU======");
		System.out.println("1. Cap nhat NV khong du dieu kien");
		System.out.println("2. Cap nhat mui 1");
		System.out.println("3. Cap nhat mui 2");
		System.out.println("4. Tim kiem theo loai vacxin");
		System.out.println("5. Thong ke");
		System.out.println("6. Thoat");
		System.out.print("Lua chon: ");
	}
	
	public void insertData() {
		// insert phong ban
		PhongBan pb1 = new PhongBan();
		pb1.setTenPB("PB_01");
		pb1 = phongBanDAO.insertPhongBan(pb1);

		PhongBan pb2 = new PhongBan();
		pb2.setTenPB("PB_02");
		pb2 = phongBanDAO.insertPhongBan(pb2);

		PhongBan pb3 = new PhongBan();
		pb3.setTenPB("PB_03");
		pb3 = phongBanDAO.insertPhongBan(pb3);

		// insert nhan vien
		NhanVien nv = new NhanVien();
		for (int i = 1; i <= 12; i++) {
			nv.setTenNV("NV_" + i);
			if (i <= 2) {
				nv.setPhongBan(pb1);

				nhanVienDAO.insertNhanVien(nv);
			} else if (i <= 6) {
				nv.setPhongBan(pb2);
				nhanVienDAO.insertNhanVien(nv);
			} else if (i <= 12) {
				nv.setPhongBan(pb3);
				nhanVienDAO.insertNhanVien(nv);
			}
		}

		// insert vac xin
		VacXin vx = new VacXin();
		vx.setTenVX("Moderna");
		vacXinDAO.insertVacXin(vx);

		vx.setTenVX("Pfizer");
		vacXinDAO.insertVacXin(vx);

		vx.setTenVX("AstraZeneca");
		vacXinDAO.insertVacXin(vx);
	}
}
