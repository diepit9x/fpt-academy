package fa.training.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "SALARIES")
public class Salary {
	@Id
	@ManyToOne
	@JoinColumn(name = "EmpId")
	private Employee employee;
	
	@Id
	@Column(name = "FromDate")
	private LocalDate fromDate;
	
	@Column(name = "Salary")
	private Double salary;
	
	@Column(name = "toDate")
	private LocalDate toDate;

	public Salary() {
		super();
	}

	public Salary(Employee employee, LocalDate fromDate, Double salary, LocalDate toDate) {
		super();
		this.employee = employee;
		this.fromDate = fromDate;
		this.salary = salary;
		this.toDate = toDate;
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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "Salary [employee=" + employee + ", fromDate=" + fromDate + ", salary=" + salary + ", toDate=" + toDate
				+ "]";
	}
	
	
}
