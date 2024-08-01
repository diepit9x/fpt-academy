package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.entities.Employee;

public interface EmployeeDAO {
    /**
     * Add an employee to database
     * @param employee
     * @return
     * @throws SQLException
     */
    boolean addEmployee(Employee employee) throws SQLException;
    
    /**
     * Update an employee
     * @param employeeId
     * @param employee
     * @return
     * @throws SQLException
     */
    boolean updateEmployee(int employeeId, Employee employee) throws SQLException;
    
    /**
     * Delete an employee by employeeId
     * @param employeeId
     * @return
     * @throws SQLException
     */
    boolean deleteEmplyee(int employeeId) throws SQLException;
    
    /**.
     * Get all employees
     * @return
     * @throws SQLException
     */
    List<Employee> getAllEmployees() throws SQLException;
    
    /**
     * Find an employee by employeeId
     * @param employeeId
     * @return
     * @throws SQLException
     */
    Employee findEmployeeById(int employeeId) throws SQLException;
}
