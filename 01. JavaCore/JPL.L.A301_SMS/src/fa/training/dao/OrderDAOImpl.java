package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Order;
import fa.training.util.DBUtils;
import fa.training.util.SQLCommand;

public class OrderDAOImpl implements OrderDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;
    private LineItemDAO lineItemDAO = null;

    @Override
    public List<Order> getAllOrdersByCustomerId(int customerId) throws SQLException {
	List<Order> orders = new ArrayList<>();
	Order order = null;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(SQLCommand.ORDER_QUERY_FIND_BY_CUSTOMERID);
	    preparedStatement.setInt(1, customerId);
	    results = preparedStatement.executeQuery();
	    while (results.next()) {
		order = new Order();
		order.setOrderId(results.getInt("order_id"));
		order.setOrderDate(results.getDate("order_date"));
		order.setCustomerId(results.getInt("customer_id"));
		order.setEmployeeId(results.getInt("employee_id"));
		order.setTotal(results.getDouble("total"));
		orders.add(order);
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
	return orders;
    }

    @Override
    public Order findOrderById(int orderId) throws SQLException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean updateOrderTotal(int orderId) throws SQLException {
	try {
	    //LineItemDAO
	    lineItemDAO = new LineItemDAOImpl();
	    double total = lineItemDAO.computeOrderTotal(orderId);
	    //Connect
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(SQLCommand.ORDER_TOTAL_UPDATE);
	    preparedStatement.setDouble(1, total);
	    preparedStatement.setInt(2, orderId);

	    int rowsAffected = preparedStatement.executeUpdate();
	    if (rowsAffected > 0) {
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
    public boolean addOrder(Order order) throws SQLException {
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(SQLCommand.ORDER_QUERY_ADD);
	    preparedStatement.setDate(1, order.getOrderDateOfSqlDate());
	    preparedStatement.setInt(2, order.getCustomerId());
	    preparedStatement.setInt(3, order.getEmployeeId());
	    preparedStatement.setDouble(4, order.getTotal());

	    int rowsAffected = preparedStatement.executeUpdate();
	    if (rowsAffected > 0) {
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
    public boolean updateOrder(Order order) throws SQLException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean deleteOrder(int orderId) throws SQLException {
	// TODO Auto-generated method stub
	return false;
    }

}
