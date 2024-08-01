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
@Table(schema = "dbo", name = "VAC_XIN")
public class Vacxin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ten_vacxin")
	private String tenVacxin;
	
	@Column(name = "nuoc_san_xuat")
	private String nuocSanXuat;
	
	@Column(name = "ngay_san_xuat")
	private LocalDate ngaySanXuat;
	
	@Column(name = "gia")
	private Integer gia;
	
	@OneToMany(mappedBy = "vacxin")
	private List<LichSuTiemChung> lichSuTiemChungs;

	public Vacxin() {
		super();
	}

	public Vacxin(Integer id, String tenVacxin, String nuocSanXuat, LocalDate ngaySanXuat, Integer gia) {
		super();
		this.id = id;
		this.tenVacxin = tenVacxin;
		this.nuocSanXuat = nuocSanXuat;
		this.ngaySanXuat = ngaySanXuat;
		this.gia = gia;
	}

	public Vacxin(String tenVacxin, String nuocSanXuat, LocalDate ngaySanXuat, Integer gia) {
		super();
		this.tenVacxin = tenVacxin;
		this.nuocSanXuat = nuocSanXuat;
		this.ngaySanXuat = ngaySanXuat;
		this.gia = gia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenVacxin() {
		return tenVacxin;
	}

	public void setTenVacxin(String tenVacxin) {
		this.tenVacxin = tenVacxin;
	}

	public String getNuocSanXuat() {
		return nuocSanXuat;
	}

	public void setNuocSanXuat(String nuocSanXuat) {
		this.nuocSanXuat = nuocSanXuat;
	}

	public LocalDate getNgaySanXuat() {
		return ngaySanXuat;
	}

	public void setNgaySanXuat(LocalDate ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}

	public Integer getGia() {
		return gia;
	}

	public void setGia(Integer gia) {
		this.gia = gia;
	}

	public List<LichSuTiemChung> getLichSuTiemChungs() {
		return lichSuTiemChungs;
	}

	public void setLichSuTiemChungs(List<LichSuTiemChung> lichSuTiemChungs) {
		this.lichSuTiemChungs = lichSuTiemChungs;
	}

	@Override
	public String toString() {
		return "Vacxin [id=" + id + ", tenVacxin=" + tenVacxin + ", nuocSanXuat=" + nuocSanXuat + ", ngaySanXuat="
				+ ngaySanXuat + ", gia=" + gia + "]";
	}
	
}
