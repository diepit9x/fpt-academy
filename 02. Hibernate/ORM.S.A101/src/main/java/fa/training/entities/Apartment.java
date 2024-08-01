package fa.training.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(schema = "dbo", name = "Apartment")
public class Apartment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "apartment_id")
	private Integer id;
	
	@Column(name = "appartment_code")
	private String appartmentCode;
	
	@Column(name = "number_bedroom")
	private Integer numberBedroom;
	
	@Column(name = "door_direction")
	private String doorDirection;
	
	@Min(value = 0, message = "gia ban phai >= 0")
	private Double price;
	
	@Pattern(regexp = "da ban|chua ban", message = "Status gom: da ban/chua ban")
	private String status;
	
	@OneToOne(mappedBy = "apartment")
	private Bill bill;

	public Apartment() {
		super();
	}

	public Apartment(Integer id, String appartmentCode, Integer numberBedroom, String doorDirection,
			@Min(value = 0, message = "gia ban phai >= 0") Double price,
			@Pattern(regexp = "da ban|chua ban", message = "Status gom: da ban/chua ban") String status, Bill bill) {
		super();
		this.id = id;
		this.appartmentCode = appartmentCode;
		this.numberBedroom = numberBedroom;
		this.doorDirection = doorDirection;
		this.price = price;
		this.status = status;
		this.bill = bill;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppartmentCode() {
		return appartmentCode;
	}

	public void setAppartmentCode(String appartmentCode) {
		this.appartmentCode = appartmentCode;
	}

	public Integer getNumberBedroom() {
		return numberBedroom;
	}

	public void setNumberBedroom(Integer numberBedroom) {
		this.numberBedroom = numberBedroom;
	}

	public String getDoorDirection() {
		return doorDirection;
	}

	public void setDoorDirection(String doorDirection) {
		this.doorDirection = doorDirection;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	@Override
	public String toString() {
		return "Appartment [id=" + id + ", appartmentCode=" + appartmentCode + ", numberBedroom=" + numberBedroom
				+ ", doorDirection=" + doorDirection + ", price=" + price + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apartment other = (Apartment) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
