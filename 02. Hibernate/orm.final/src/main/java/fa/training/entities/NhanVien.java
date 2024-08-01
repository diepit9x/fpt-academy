package fa.training.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "NHAN_VIEN")
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ten_nhan_vien")
	private String tenNhanVien;
	
	@Column(name = "gioi_tinh")
	private String gioiTinh;
	
	@Column(name = "dia_chi")
	private String diaChi;
	
	@Column(name = "dien_thoai")
	private String dienThoai;
	
	@Column(name = "nam_kinh_nghiem")
	private Integer namKinhNghiem;
	
	@OneToMany(mappedBy = "nhanVien")
	private List<LichSuTiemChung> lichSuTiemChungs;

	public NhanVien() {
		super();
	}

	public NhanVien(Integer id, String tenNhanVien, String gioiTinh, String diaChi, String dienThoai,
			Integer namKinhNghiem) {
		super();
		this.id = id;
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.namKinhNghiem = namKinhNghiem;
	}

	public NhanVien(String tenNhanVien, String gioiTinh, String diaChi, String dienThoai, Integer namKinhNghiem) {
		super();
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.namKinhNghiem = namKinhNghiem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
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

	public Integer getNamKinhNghiem() {
		return namKinhNghiem;
	}

	public void setNamKinhNghiem(Integer namKinhNghiem) {
		this.namKinhNghiem = namKinhNghiem;
	}

	public List<LichSuTiemChung> getLichSuTiemChungs() {
		return lichSuTiemChungs;
	}

	public void setLichSuTiemChungs(List<LichSuTiemChung> lichSuTiemChungs) {
		this.lichSuTiemChungs = lichSuTiemChungs;
	}

	@Override
	public String toString() {
		return "NhanVien [id=" + id + ", tenNhanVien=" + tenNhanVien + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi
				+ ", dienThoai=" + dienThoai + ", namKinhNghiem=" + namKinhNghiem + "]";
	}
	
	
}
