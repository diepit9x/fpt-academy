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
@Table(schema = "dbo", name = "PHONG_BAN")
public class PhongBan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PB")
	private Integer idPB;
	
	@Column(name = "TEN_PB")
	private String TenPB;
	
	@OneToMany(mappedBy = "phongBan")
	private List<NhanVien> nhanViens;

	public PhongBan() {
		super();
	}

	public PhongBan(Integer idPB, String tenPB, List<NhanVien> nhanViens) {
		super();
		this.idPB = idPB;
		TenPB = tenPB;
		this.nhanViens = nhanViens;
	}

	public Integer getIdPB() {
		return idPB;
	}

	public void setIdPB(Integer idPB) {
		this.idPB = idPB;
	}

	public String getTenPB() {
		return TenPB;
	}

	public void setTenPB(String tenPB) {
		TenPB = tenPB;
	}

	public List<NhanVien> getNhanViens() {
		return nhanViens;
	}

	public void setNhanViens(List<NhanVien> nhanViens) {
		this.nhanViens = nhanViens;
	}

	@Override
	public String toString() {
		return "PhongBan [idPB=" + idPB + ", TenPB=" + TenPB + "]";
	}
	
	
}
