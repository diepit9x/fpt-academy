package fa.training.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(schema = "dbo", name = "Bill")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "employee_id")
	@NotNull(message = "employee must be not null")
	private Employee employee;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "apartment_id")
	@NotNull(message = "apartment must be not null")
	private Apartment apartment;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id")
	@NotNull(message = "customer must be not null")
	private Customer customer;
	
	@Column(name = "buy_date")
	private LocalDate buyDate;
	
	@Min(value = 0, message = "gia ban phai >= 0")
	private Double price;

	public Bill() {
		super();
	}

	public Bill(Integer id, Employee employee, Apartment apartment, Customer customer, LocalDate buyDate,
			@Min(value = 0, message = "gia ban phai >= 0") Double price) {
		super();
		this.id = id;
		this.employee = employee;
		this.apartment = apartment;
		this.customer = customer;
		this.buyDate = buyDate;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment appartment) {
		this.apartment = appartment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(LocalDate buyDate) {
		this.buyDate = buyDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Bill [id=" + id + ", employee_id=" + employee.getId() + ", appartment_id=" + apartment.getId() + ", customer_id=" + customer.getId()
				+ ", buyDate=" + buyDate + ", price=" + price + "]";
	}
	
	
}
