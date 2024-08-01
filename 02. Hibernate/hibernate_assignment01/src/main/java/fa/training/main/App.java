package fa.training.main;

import java.util.ArrayList;
import java.util.List;

import fa.training.dao.EmployeeDAO;
import fa.training.dao.EmployeeDAOImpl;
import fa.training.entities.Employee;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		try {
			List<Employee> employees = employeeDAO.getAllEmployees();
			for (Employee employee : employees) {
				System.out.println(employee.toString());
			}
			
			//add new employee
			Employee e1 = new Employee("f1", "l1");
			Employee e2 = new Employee("f2", "l2");
			Employee e3 = new Employee("f3", "l3");
			
			List<Employee> employees2 = new ArrayList<>();
			employees2.add(e1);
			employees2.add(e2);
			
//			if (employeeDAO.insertAllEmployees(employees2)) {
//				System.out.println("add employee list successfully");
//			} else {
//				System.out.println("add employee list failure");
//			}
//			
//			if (employeeDAO.insertEmployee(e3)) {
//				System.out.println("add an employee successfully");
//			} else {
//				System.out.println("add an employee failure");
//			}
			
			
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
