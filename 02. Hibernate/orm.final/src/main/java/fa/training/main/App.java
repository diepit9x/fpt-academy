package fa.training.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import fa.training.dao.ChiNhanhDAO;
import fa.training.dao.KhachHangDAO;
import fa.training.dao.LichSuTiemChungDAO;
import fa.training.dao.NhanVienDAO;
import fa.training.dao.VacxinDAO;
import fa.training.entities.ChiNhanh;
import fa.training.entities.KhachHang;
import fa.training.entities.LichSuTiemChung;
import fa.training.entities.NhanVien;
import fa.training.entities.Vacxin;
import fa.training.util.HibernateUtil;

public class App {
	private static ChiNhanhDAO chiNhanhDAO = new ChiNhanhDAO();
	private static KhachHangDAO khachHangDAO = new KhachHangDAO();
	private static LichSuTiemChungDAO lichSuTiemChungDAO = new LichSuTiemChungDAO();
	private static NhanVienDAO nhanVienDAO = new NhanVienDAO();
	private static VacxinDAO vacxinDAO = new VacxinDAO();
	
	
	public static void main(String[] args) {
		Scanner scanner = null;
		App app = new App();
		String choice;
		boolean exit = false;
		insertData();
		try {
			scanner = new Scanner(System.in);
			do {
				app.menu();
				choice = scanner.nextLine();
				try {
					
					switch (choice) {
						case "1": {
							app.choTiem();
							break;
						}
						case "2": {
							app.phanCong(scanner);
							break;
						}
						case "3": {
							app.hoanThanh(scanner);
							break;
						}
						case "4": {
							exit = true;
							break;
						}
						default:
							System.out.println("Lua chon khong phu hop");
							continue;
					}
					
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
				if (exit) {
					break;
				}
			} while (true);
			
			
			
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}

	public void menu() {
		System.out.println("======MENU=======");
		System.out.println("1. Danh sach cho tiem");
		System.out.println("2. Phan cong tiem chung");
		System.out.println("3. Hoan thanh tiem chung");
		System.out.println("4. Exit");
		System.out.print("Choice: ");
	}
	
	public void hoanThanh(Scanner scanner) {
		List<LichSuTiemChung> lstcs = lichSuTiemChungDAO.choTiem();
		if (lstcs.isEmpty()) {
			System.out.println("Khong co khach hang cho tiem");
			return;
		}
		System.out.println("-------danh sach cho tiem---------");
		lstcs.forEach(l -> System.out.println(l.toString()));
		System.out.println("-------------------------------------");
		
		System.out.print("chon khachHangId: ");
		Integer id = null;
		try {
			 id = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Id khong hop le");
			return;
		}
		
//		boolean status = lichSuTiemChungDAO.hoanTat(id);
		
		boolean status = lichSuTiemChungDAO.hoanTat2(id);
		
		if (status) {
			System.out.println("Da hoan tat");
		}
		
	}
	
	
	
	public void choTiem() {
		try {
			List<KhachHang> khachHangs = lichSuTiemChungDAO.choTiemHomNay();
			if (khachHangs.isEmpty()) {
				System.out.println("Hien tai khong co khach hang nao cho tiem");
			} else {
				khachHangs.forEach(k -> System.out.println(k.toString()));
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void phanCong(Scanner scanner) {
		try {
			System.out.print("Nhap khach hang id: ");
			Integer khachHangId = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Nhap nhan vien id: ");
			Integer nhanVienId = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Nhap vacxin id: ");
			Integer vacxinID = Integer.parseInt(scanner.nextLine());
			
			boolean status = lichSuTiemChungDAO.phanCong(khachHangId, nhanVienId, vacxinID);
			if (status) {
				System.out.println("Phan cong thanh cong");
			}
			
		} catch (NumberFormatException e) {
			System.err.println("Nhap id khong hop le");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	
	public static void insertData() {
		try {
			//khach hang
			KhachHang kh1 = khachHangDAO.insert(new KhachHang("KH1", "nam", LocalDate.of(2020, 6, 1), "dia chi 1", "0987654321"));
			KhachHang kh2 = khachHangDAO.insert(new KhachHang("KH2", "nam", LocalDate.of(2015, 5, 1), "dia chi 2", "0987654320"));
			KhachHang kh3 = khachHangDAO.insert(new KhachHang("KH3", "nu", LocalDate.of(2010, 4, 1), "dia chi 3", "0987654322"));
			KhachHang kh4 = khachHangDAO.insert(new KhachHang("KH4", "nam", LocalDate.of(2024, 3, 1), "dia chi 4", "0987654323"));
			KhachHang kh5 = khachHangDAO.insert(new KhachHang("KH5", "nu", LocalDate.of(2023, 2, 1), "dia chi 5", "0987654324"));
			KhachHang kh6 = khachHangDAO.insert(new KhachHang("KH6", "nam", LocalDate.of(2015, 6 , 1), "dia chi 6", "0987654325"));
			
			//Nhan Vien
			NhanVien nv1 = nhanVienDAO.insert(new NhanVien("NV1", "nam", "dia chi 1", "09888888111", 1));
			NhanVien nv2 = nhanVienDAO.insert(new NhanVien("NV2", "nam", "dia chi 2", "09888888112", 2));
			NhanVien nv3 = nhanVienDAO.insert(new NhanVien("NV3", "nu", "dia chi 3", "09888888113", 3));
			NhanVien nv4 = nhanVienDAO.insert(new NhanVien("NV4", "nam", "dia chi 4", "09888888114", 4));
			NhanVien nv5 = nhanVienDAO.insert(new NhanVien("NV5", "nu", "dia chi 5", "09888888115", 5));
			NhanVien nv6 = nhanVienDAO.insert(new NhanVien("NV6", "nam", "dia chi 6", "09888888116", 6));
			
			//Vacxin
			Vacxin vx1 = vacxinDAO.insert(new Vacxin("6 in 1", "VIET NAM", LocalDate.of(2020, 6 , 1), 100000));
			Vacxin vx2 = vacxinDAO.insert(new Vacxin("Quai bi", "VIET NAM", LocalDate.of(2020, 6 , 1), 200000));
			Vacxin vx3 = vacxinDAO.insert(new Vacxin("Viem nao nhat ban", "Nhat ban", LocalDate.of(2020, 6 , 1), 300000));
			Vacxin vx4 = vacxinDAO.insert(new Vacxin("Thuy dau", "Trung Quoc", LocalDate.of(2020, 6 , 1), 400000));
			Vacxin vx5 = vacxinDAO.insert(new Vacxin("Cum", "VIET NAM", LocalDate.of(2020, 6 , 1), 500000));
			
			//Chi Nhanh
			ChiNhanh cn1 = chiNhanhDAO.insert(new ChiNhanh("dia chi cn1", "0988654321"));
			ChiNhanh cn2 = chiNhanhDAO.insert(new ChiNhanh("dia chi cn2", "0988654322"));
			ChiNhanh cn3 = chiNhanhDAO.insert(new ChiNhanh("dia chi cn3", "0988654323"));
			ChiNhanh cn4 = chiNhanhDAO.insert(new ChiNhanh("dia chi cn4", "0988654324"));
			ChiNhanh cn5 = chiNhanhDAO.insert(new ChiNhanh("dia chi cn5", "0988654325"));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
