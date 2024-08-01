package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.entities.Employee;

/**
 * The Interface EmployeeDAO.
 */
public interface EmployeeDAO {
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 * @throws SQLException the SQL exception
	 */
	List<Employee> getAllEmployees() throws SQLException;
	
	/**
	 * Gets the employee by id.
	 *
	 * @param employeeId the employee id
	 * @return the employee by id
	 * @throws SQLException the SQL exception
	 */
	Employee getEmployeeByID(int employeeId) throws SQLException;
	
	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	boolean insertEmployee(Employee employee) throws SQLException;
	
	/**
	 * Adds the all employees.
	 *
	 * @param employees the employees
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	boolean insertAllEmployees(List<Employee> employees) throws SQLException;
	
	/**
	 * Update employee.
	 *
	 * @param employee the employee
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	boolean updateEmployeeByID(int employeeId) throws SQLException;
	
	/**
	 * Delete employee.
	 *
	 * @param employeeId the employee id
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	boolean deleteEmployeeByID(int employeeId) throws SQLException;
}
