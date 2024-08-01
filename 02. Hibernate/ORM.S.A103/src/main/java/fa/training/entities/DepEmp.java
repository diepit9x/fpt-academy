package fa.training.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "DEP_EMP")
public class DepEmp {
	@Id
	@ManyToOne
	@JoinColumn(name = "DepId")
	private Department department;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "EmpId")
	private Employee employee;
	
	@Column(name = "FromDate")
	private LocalDate fromDate;
	
	@Column(name = "ToDate")
	private LocalDate toDate;

	public DepEmp() {
		super();
	}

	public DepEmp(Department department, Employee employee, LocalDate fromDate, LocalDate toDate) {
		super();
		this.department = department;
		this.employee = employee;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "DepEmp [department=" + department + ", employee=" + employee + ", fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}
	

}
