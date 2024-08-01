package fa.training.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(schema = "dbo", name = "KhachHang")
public class KhachHang {
    @Id
    @Pattern(regexp = "^KH\\d{5}$", message = "Mã khách hàng không hợp lệ")
    private String maKH;

    @Pattern(regexp = "^[a-zA-Z0-9\s]*$", message = "Tên khách hàng không hợp lệ")
    private String tenKH;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;

    @Pattern(regexp = "^(090|091|\\(84\\)\\+90|\\(84\\)\\+91)\\d{7}$", message = "Tên khách hàng không hợp lệ")
    private String soDienThoai;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
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
