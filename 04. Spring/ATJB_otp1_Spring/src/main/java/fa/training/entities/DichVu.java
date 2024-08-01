package fa.training.entities;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(schema = "dbo", name = "DichVu")
public class DichVu {
    @Id
    @Pattern(regexp = "^DV\\d{3}$", message = "Mã dịch vụ không hợp lệ")
    private String maDV;

    @NotBlank(message = "Vui lòng nhập tên dịch vụ")
    private String tenDV;

    @NotBlank(message = "Vui lòng chọn đơn vị tính")
    private String donViTinh;

    @Range(min = 1, max = 10000000, message = "Đơn giá phải từ 1÷10.000.000")
    @NotNull(message = "Đơn giá không được để trống")
    private Integer donGia;

    public DichVu() {
	super();
    }

    public DichVu(String maDV, String tenDV, String donViTinh, Integer donGia) {
	super();
	this.maDV = maDV;
	this.tenDV = tenDV;
	this.donViTinh = donViTinh;
	this.donGia = donGia;
    }

    public String getMaDV() {
	return maDV;
    }

    public void setMaDV(String maDV) {
	this.maDV = maDV;
    }

    public String getTenDV() {
	return tenDV;
    }

    public void setTenDV(String tenDV) {
	this.tenDV = tenDV;
    }

    public String getDonViTinh() {
	return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
	this.donViTinh = donViTinh;
    }

    public Integer getDonGia() {
	return donGia;
    }

    public void setDonGia(Integer donGia) {
	this.donGia = donGia;
    }

}
