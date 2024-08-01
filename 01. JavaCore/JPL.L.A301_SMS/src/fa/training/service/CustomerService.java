package fa.training.service;

import java.sql.SQLException;
import java.util.List;

import fa.training.dao.CustomerDAO;
import fa.training.dao.CustomerDAOImpl;
import fa.training.entities.Customer;
import fa.training.exception.DataNotFoundException;
import fa.training.util.Constant;

public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    
    /**
     * Get all customers
     * @throws SQLException
     * @throws DataNotFoundException
     */
    public void getAllCustomer() throws SQLException, DataNotFoundException {
	List<Customer> customers = customerDAO.getAllCustomer();
	if (customers.isEmpty()) {
	    throw new DataNotFoundException(Constant.CUSTOMER_LIST_IS_EMPTY);
	}
	System.out.println(String.format("%-20s %s","Customer ID", "Customer Name"));
	System.out.println("----------------------------------------------");
	for (Customer customer : customers) {
	    String stringFormat = String.format("%-30d %s", customer.getCustomerId(), customer.getCustomerName());
	    System.out.println(stringFormat);
	}
    }
    
    /**
     * Find a customer by customerId
     * @param customerId
     * @throws SQLException
     * @throws DataNotFoundException
     */
    public void findCustomerById(int customerId) throws SQLException, DataNotFoundException {
	Customer customer = customerDAO.findCustomerById(customerId);
	if (customer == null) {
	    throw new DataNotFoundException(Constant.CUSTOMER_NOT_FOUND);
	}
	System.out.println(String.format("%-20s %s","Customer ID", "Customer Name"));
	System.out.println("----------------------------------------------");
	String stringFormat = String.format("%-30d %s", customer.getCustomerId(), customer.getCustomerName());
	System.out.println(stringFormat);
    }
    
    /**
     * add customer
     * @param customer
     * @throws SQLException
     */
    public void addCustomer(Customer customer) throws SQLException {
	boolean status = customerDAO.addCustomer(customer);
	if (status) {
	    System.out.println("Add a new customer successfully");
	}
    }
    
    /**
     * delete customer
     * @param customerId
     * @throws SQLException
     */
    public void deleteCustomer(int customerId) throws SQLException {
	boolean status = customerDAO.deleteCustomer(customerId);
	if (status) {
	    System.out.println("delete customerid = " + customerId +" successfully");
	}
    }
    
    /**
     * update customer
     * @param customer
     * @throws SQLException
     */
    public void updateCustomer(Customer customer) throws SQLException {
	boolean status = customerDAO.updateCustomer(customer);
	if (status) {
		System.out.println("upadte successfully");
	    } else {
		System.err.println("update failure");
	    }
    }
}
