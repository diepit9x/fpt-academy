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
@Table(schema = "dbo", name = "CHI_NHANH")
public class ChiNhanh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "dia_chi")
	private String diaChi;
	
	@Column(name = "so_dien_thoai")
	private String soDienThoai;
	
	@OneToMany(mappedBy = "chiNhanh")
	private List<LichSuTiemChung> lichSuTiemChungs;

	public ChiNhanh() {
		super();
	}

	public ChiNhanh(Integer id, String diaChi, String soDienThoai) {
		super();
		this.id = id;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public ChiNhanh(String diaChi, String soDienThoai) {
		super();
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public List<LichSuTiemChung> getLichSuTiemChungs() {
		return lichSuTiemChungs;
	}

	public void setLichSuTiemChungs(List<LichSuTiemChung> lichSuTiemChungs) {
		this.lichSuTiemChungs = lichSuTiemChungs;
	}

	@Override
	public String toString() {
		return "ChiNhanh [id=" + id + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + "]";
	}
	
	
}
