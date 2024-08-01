package entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "KhachHang")
public class KhachHang {
	@Id
	private String maKH;
	private String tenKH;
	private String diaChi;
	private String soDienThoai;
	private String email;
	
	@OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
	private List<SuDungMay> suDungMays;
	
	@OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
	private List<SuDungDichVu> suDungDichVus;

	public KhachHang() {
		super();
	}

	public KhachHang(String maKH, String tenKH, String diaChi, String soDienThoai, String email) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.email = email;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<SuDungMay> getSuDungMays() {
		return suDungMays;
	}

	public void setSuDungMays(List<SuDungMay> suDungMays) {
		this.suDungMays = suDungMays;
	}

	public List<SuDungDichVu> getSuDungDichVus() {
		return suDungDichVus;
	}

	public void setSuDungDichVus(List<SuDungDichVu> suDungDichVus) {
		this.suDungDichVus = suDungDichVus;
	}
 
}