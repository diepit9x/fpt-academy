package fa.training.entities;

public class Employee {
    private int employeeId;
    private String employeeName;
    private double salary;
    private int spvrId;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, double salary, int spvrId) {
	super();
	this.employeeId = employeeId;
	this.employeeName = employeeName;
	this.salary = salary;
	this.spvrId = spvrId;
    }

    public int getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(String employeeId) throws NumberFormatException {
	this.employeeId = Integer.parseInt(employeeId);
    }

    public String getEmployeeName() {
	return employeeName;
    }

    public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
    }

    public double getSalary() {
	return salary;
    }

    public void setSalary(String salary) throws NumberFormatException {
	this.salary = Double.parseDouble(salary);
    }

    public int getSpvrId() {
	return spvrId;
    }

    public void setSpvrId(int spvrId) {
	this.spvrId = spvrId;
    }

}
