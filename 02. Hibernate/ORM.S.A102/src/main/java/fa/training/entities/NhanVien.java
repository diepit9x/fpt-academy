package fa.training.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "NHAN_VIEN")
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_NV")
	private Integer IdNV;
	
	@Column(name = "TEN_NV")
	private String tenNV;
	
	@ManyToOne
	@JoinColumn(name = "ID_PB")
	private PhongBan phongBan;
	
	@OneToOne(mappedBy = "nhanVien", cascade = CascadeType.ALL)
	private ThongTinTiemChung thongTinTiemChung;

	public NhanVien() {
		super();
	}

	public NhanVien(Integer idNV, String tenNV, PhongBan phongBan, ThongTinTiemChung thongTinTiemChung) {
		super();
		IdNV = idNV;
		this.tenNV = tenNV;
		this.phongBan = phongBan;
		this.thongTinTiemChung = thongTinTiemChung;
	}

	public Integer getIdNV() {
		return IdNV;
	}

	public void setIdNV(Integer idNV) {
		IdNV = idNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public PhongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}

	public ThongTinTiemChung getThongTinTiemChung() {
		return thongTinTiemChung;
	}

	public void setThongTinTiemChung(ThongTinTiemChung thongTinTiemChung) {
		this.thongTinTiemChung = thongTinTiemChung;
	}

	@Override
	public String toString() {
		return "NhanVien [IdNV=" + IdNV + ", tenNV=" + tenNV + ", phongBan=" + phongBan.toString() + "]";
	}
	
	
}
