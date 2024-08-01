package fa.training.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(schema = "dbo", name = "Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Integer id;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Pattern(regexp = "NV|QL", message = "Role gom: NV/QL")
	private String role;
	
	@OneToMany(mappedBy = "employee")
	private List<Bill> bills;

	public Employee() {
		super();
	}

	public Employee(Integer id, String employeeName,
			@Pattern(regexp = "NV|QL", message = "Role gom: NV/QL") String role, List<Bill> bills) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.role = role;
		this.bills = bills;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", role=" + role + "]";
	}
	
	
}
