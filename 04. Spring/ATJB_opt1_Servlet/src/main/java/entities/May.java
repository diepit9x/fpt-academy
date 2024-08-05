package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(schema = "dbo" ,name = "May")
public class May {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maMay;
	@NotBlank(message = "Vị trí không được để trống")
	private String viTri;
	
	@NotBlank(message = "Trạng thái không được để trống")
	private String trangThai;
	public May() {
		super();
	}
	public May(Integer maMay, String viTri, String trangThai) {
		super();
		this.maMay = maMay;
		this.viTri = viTri;
		this.trangThai = trangThai;
	}
	public Integer getMaMay() {
		return maMay;
	}
	public void setMaMay(Integer maMay) {
		this.maMay = maMay;
	}
	public String getViTri() {
		return viTri;
	}
	public void setViTri(String viTri) {
		this.viTri = viTri;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
