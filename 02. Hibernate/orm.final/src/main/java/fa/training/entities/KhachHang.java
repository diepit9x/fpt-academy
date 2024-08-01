package fa.training.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "KHACH_HANG")
public class KhachHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ho_ten")
	private String hoTen;
	
	@Column(name = "gioi_tinh")
	private String gioiTinh;
	
	@Column(name = "ngay_sinh")
	private LocalDate ngaySinh;
	
	@Column(name = "dia_chi")
	private String diaChi;
	
	@Column(name = "dien_thoai")
	private String dienThoai;
	
	@OneToMany(mappedBy = "khachHang")
	private List<LichSuTiemChung> lichSuTiemChungs;

	public KhachHang() {
		super();
	}

	public KhachHang(Integer id, String hoTen, String gioiTinh, LocalDate ngaySinh, String diaChi, String dienThoai) {
		super();
		this.id = id;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
	}

	public KhachHang(String hoTen, String gioiTinh, LocalDate ngaySinh, String diaChi, String dienThoai) {
		super();
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public List<LichSuTiemChung> getLichSuTiemChungs() {
		return lichSuTiemChungs;
	}

	public void setLichSuTiemChungs(List<LichSuTiemChung> lichSuTiemChungs) {
		this.lichSuTiemChungs = lichSuTiemChungs;
	}

	@Override
	public String toString() {
		return "KhachHang [id=" + id + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh
				+ ", diaChi=" + diaChi + ", dienThoai=" + dienThoai + "]";
	}
	
}
