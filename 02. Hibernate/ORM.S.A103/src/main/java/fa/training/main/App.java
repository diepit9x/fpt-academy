package fa.training.main;

import java.time.LocalDate;

import fa.training.dao.DepEmpDAO;
import fa.training.dao.DepartmentDAO;
import fa.training.dao.EmployeeDAO;
import fa.training.dao.SalaryDAO;
import fa.training.entities.Department;
import fa.training.entities.Employee;
import fa.training.entities.Salary;
import fa.training.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
	private static DepartmentDAO departmentDAO = new DepartmentDAO();
	private static DepEmpDAO depEmpDAO = new DepEmpDAO();
	private static EmployeeDAO employeeDAO = new EmployeeDAO();
	private static SalaryDAO salaryDAO = new SalaryDAO();
	
    public static void main( String[] args )
    {
        App app = new App();
        app.insertData();
        
        
    	Employee e2 = new Employee();
    	e2.setId("001");
    	
    	Salary s2 = new Salary();
    	s2.setEmployee(e2);
    	s2.setSalary(20000000D);
    	s2.setFromDate(LocalDate.of(2012, 2, 22));
    	s2.setToDate(LocalDate.of(2013, 2, 22));
    	salaryDAO.insert(s2);
        
        
    }
    
    public void insertData() {
		//insert department
    	Department d1 = new Department();
    	d1.setId("DEP01");
    	d1.setName("Finance");
    	departmentDAO.insert(d1);
    	
    	Department d2 = new Department();
    	d2.setId("DEP02");
    	d2.setName("Accounting");
    	departmentDAO.insert(d2);
    	
    	//insert employee
    	Employee e1 = new Employee();
    	e1.setId("001");
    	e1.setName("Nguyen A");
    	e1.setHireDate(LocalDate.of(2011, 1, 21));
    	e1.setExpirationDate(LocalDate.of(2021, 1, 21));
    	employeeDAO.insert(e1);
    	
    	Employee e2 = new Employee();
    	e2.setId("002");
    	e2.setName("Nguyen B");
    	e2.setHireDate(LocalDate.of(2012, 2, 22));
    	e2.setExpirationDate(LocalDate.of(2022, 2, 22));
    	employeeDAO.insert(e2);
    	
    	//insert salary
    	Salary s1 = new Salary();
    	s1.setEmployee(e1);
    	s1.setSalary(10000000D);
    	s1.setFromDate(LocalDate.of(2011, 1, 21));
    	s1.setToDate(LocalDate.of(2012, 1, 21));
    	salaryDAO.insert(s1);
    	
    	Salary s2 = new Salary();
    	s2.setEmployee(e2);
    	s2.setSalary(20000000D);
    	s2.setFromDate(LocalDate.of(2012, 2, 22));
    	s2.setToDate(LocalDate.of(2013, 2, 22));
    	salaryDAO.insert(s2);
    	
    	
	}
}
