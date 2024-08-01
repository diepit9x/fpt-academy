package fa.training.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Customer;
import fa.training.util.DBUtils;
import fa.training.util.SQLCommand;

public class CustomerDAOImpl implements CustomerDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private CallableStatement callableStatement = null;
    private ResultSet results = null;
    
    @Override
    public List<Customer> getAllCustomer() throws SQLException {
	List<Customer> customers = new ArrayList<>();
	Customer customer = null;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(SQLCommand.CUSTOMER_QUERY_FIND_ALL_CUSTOMERS);
	    results = preparedStatement.executeQuery();
	    while (results.next()) {
		customer = new Customer();
		customer.setCustomerId(results.getInt("customer_id"));
		customer.setCustomerName(results.getString("customer_name"));
		customers.add(customer);
	    }
	} finally {
	    try {
		if (connection != null) {
		    connection.close();
		}
		if (preparedStatement != null) {
		    preparedStatement.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return customers;
    }

    @Override
    public Customer findCustomerById(final int customerId) throws SQLException {
	Customer customer = null;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(SQLCommand.CUSTOMER_QUERY_FIND_BY_CUSTOMERID);
	    preparedStatement.setInt(1, customerId);
	    results = preparedStatement.executeQuery();
	    while (results.next()) {
		customer = new Customer();
		customer.setCustomerId(results.getInt("customer_id"));
		customer.setCustomerName(results.getString("customer_name"));
	    }
	} finally {
	    try {
		if (connection != null) {
		    connection.close();
		}
		if (preparedStatement != null) {
		    preparedStatement.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return customer;
    }

    @Override
    public boolean addCustomer(final Customer customer) throws SQLException {
	try {
	    connection = DBUtils.getInstance().getConnection();
	    callableStatement = connection.prepareCall(SQLCommand.CUSTOMER_QUERY_ADD);
	    callableStatement.setString(1, customer.getCustomerName());
	    callableStatement.registerOutParameter(2, Types.INTEGER);
	    callableStatement.execute();
	    if (callableStatement.getInt(2) > 0) {
//		System.out.println("new customer id: " + callableStatement.getInt(2));
		return true;
	    }
	} finally {
	    try {
		if (connection != null) {
		    connection.close();
		}
		if (preparedStatement != null) {
		    preparedStatement.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return false;
    }

    @Override
    public boolean deleteCustomer(final int customerId) throws SQLException {
	try {
	    connection = DBUtils.getInstance().getConnection();
	    callableStatement = connection.prepareCall(SQLCommand.CUSTOMER_QUERY_DELETE);
	    callableStatement.setInt(1, customerId);
	    callableStatement.registerOutParameter(2, Types.BOOLEAN);
	    callableStatement.execute();
	    if (callableStatement.getBoolean(2)) {
		return true;
	    }
	} finally {
	    try {
		if (connection != null) {
		    connection.close();
		}
		if (preparedStatement != null) {
		    preparedStatement.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return false;
    }

    @Override
    public boolean updateCustomer(final Customer customer) throws SQLException {
	try {
	    connection = DBUtils.getInstance().getConnection();
	    callableStatement = connection.prepareCall(SQLCommand.CUSTOMER_QUERY_UPDATE);
	    callableStatement.setInt(1, customer.getCustomerId());
	    callableStatement.setString(2, customer.getCustomerName());
	    callableStatement.registerOutParameter(3, Types.BOOLEAN);
	    callableStatement.execute();
	    if (callableStatement.getBoolean(3) ) {
		return true;
	    }
	} finally {
	    try {
		if (connection != null) {
		    connection.close();
		}
		if (preparedStatement != null) {
		    preparedStatement.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return false;
    }
}
