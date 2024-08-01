package fa.training.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "VAC_XIN")
public class VacXin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VX")
	private Integer IdVX;
	
	@Column(name = "TEN_VX")
	private String tenVX;

	public VacXin() {
		super();
	}

	public VacXin(Integer idVX, String tenVX) {
		super();
		IdVX = idVX;
		this.tenVX = tenVX;
	}

	public Integer getIdVX() {
		return IdVX;
	}

	public void setIdVX(Integer idVX) {
		IdVX = idVX;
	}

	public String getTenVX() {
		return tenVX;
	}

	public void setTenVX(String tenVX) {
		this.tenVX = tenVX;
	}

	@Override
	public String toString() {
		return "VacXin [IdVX=" + IdVX + ", tenVX=" + tenVX + "]";
	}
}
