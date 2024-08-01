package fa.training.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "EMPLOYEES")
public class Employee {
	@Id
	@Column(name = "Id")
	private String id;
	
	@Column(name = "Name")
	private String name;

	@Column(name = "HireDate")
	private LocalDate hireDate;

	@Column(name = "ExpirationDate")
	private LocalDate expirationDate;
	
	@OneToMany(mappedBy = "employee")
	private List<DepEmp> depEmps;
	
	@OneToMany(mappedBy = "employee")
	private List<Salary> salaries;

	public Employee() {
		super();
	}

	public Employee(String id, String name, LocalDate hireDate, LocalDate expirationDate, List<DepEmp> depEmps,
			List<Salary> salaries) {
		super();
		this.id = id;
		this.name = name;
		this.hireDate = hireDate;
		this.expirationDate = expirationDate;
		this.depEmps = depEmps;
		this.salaries = salaries;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public List<DepEmp> getDepEmps() {
		return depEmps;
	}

	public void setDepEmps(List<DepEmp> depEmps) {
		this.depEmps = depEmps;
	}

	public List<Salary> getSalaries() {
		return salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", hireDate=" + hireDate + ", expirationDate=" + expirationDate
				+ "]";
	}
	
}
