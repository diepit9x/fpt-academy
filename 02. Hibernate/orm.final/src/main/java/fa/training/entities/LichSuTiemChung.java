package fa.training.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "LICH_SU_TIEM_CHUNG")
public class LichSuTiemChung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "khach_hang_id")
	private KhachHang khachHang;
	
	@ManyToOne
	@JoinColumn(name = "vacxin_id")
	private Vacxin vacxin;
	
	@ManyToOne
	@JoinColumn(name = "nhan_vien_id")
	private NhanVien nhanVien;
	
	@Column(name = "ngay_tiem")
	private LocalDate ngayTiem;
	
	@ManyToOne
	@JoinColumn(name = "chi_nhanh")
	private ChiNhanh chiNhanh;
	
	@Column(name = "trang_thai")
	private String trangThai;
	
	@Column(name = "tong_tien")
	private Integer tongTien;

	public LichSuTiemChung() {
		super();
	}

	public LichSuTiemChung(Integer id, KhachHang khachHang, Vacxin vacxin, NhanVien nhanVien, LocalDate ngayTiem,
			ChiNhanh chiNhanh, String trangThai, Integer tongTien) {
		super();
		this.id = id;
		this.khachHang = khachHang;
		this.vacxin = vacxin;
		this.nhanVien = nhanVien;
		this.ngayTiem = ngayTiem;
		this.chiNhanh = chiNhanh;
		this.trangThai = trangThai;
		this.tongTien = tongTien;
	}

	public LichSuTiemChung(KhachHang khachHang, Vacxin vacxin, NhanVien nhanVien, LocalDate ngayTiem, ChiNhanh chiNhanh,
			String trangThai, Integer tongTien) {
		super();
		this.khachHang = khachHang;
		this.vacxin = vacxin;
		this.nhanVien = nhanVien;
		this.ngayTiem = ngayTiem;
		this.chiNhanh = chiNhanh;
		this.trangThai = trangThai;
		this.tongTien = tongTien;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Vacxin getVacxin() {
		return vacxin;
	}

	public void setVacxin(Vacxin vacxin) {
		this.vacxin = vacxin;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public LocalDate getNgayTiem() {
		return ngayTiem;
	}

	public void setNgayTiem(LocalDate ngayTiem) {
		this.ngayTiem = ngayTiem;
	}

	public ChiNhanh getChiNhanh() {
		return chiNhanh;
	}

	public void setChiNhanh(ChiNhanh chiNhanh) {
		this.chiNhanh = chiNhanh;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public Integer getTongTien() {
		return tongTien;
	}

	public void setTongTien(Integer tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public String toString() {
		return "LichSuTiemChung [id=" + id + ", khachHang=" + khachHang + ", vacxin=" + vacxin + ", nhanVien="
				+ nhanVien + ", ngayTiem=" + ngayTiem + ", chiNhanh=" + chiNhanh + ", trangThai=" + trangThai
				+ ", tongTien=" + tongTien + "]";
	}
	
	
}
