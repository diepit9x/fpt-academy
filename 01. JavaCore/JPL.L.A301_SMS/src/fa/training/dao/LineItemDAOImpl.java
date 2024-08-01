package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.LineItem;
import fa.training.util.DBUtils;
import fa.training.util.SQLCommand;

public class LineItemDAOImpl implements LineItemDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    @Override
    public List<LineItem> getAllItemsByOrderId(int orderId) throws SQLException {
	List<LineItem> lineItems = new ArrayList<>();
	LineItem lineItem = null;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(SQLCommand.LINEITEM_QUERY_FIND_BY_ORDERID);
	    preparedStatement.setInt(1, orderId);
	    results = preparedStatement.executeQuery();
	    while (results.next()) {
		lineItem = new LineItem();
		lineItem.setOrderId(results.getInt("order_id"));
		lineItem.setProductId(results.getInt("product_id"));
		lineItem.setQuantity(results.getInt("quantity"));
		lineItem.setPrice(results.getDouble("price"));
		lineItems.add(lineItem);
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
	return lineItems;
    }

    @Override
    public double computeOrderTotal(int orderId) throws SQLException {
	double total = 0;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(SQLCommand.LINEITEM_QUERY_COMPUTE_ORDER_TOTAL);
	    preparedStatement.setInt(1, orderId);
	    results = preparedStatement.executeQuery();
	    while(results.next()) {
		total = results.getDouble("total_price");
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
	return total;
    }

    @Override
    public boolean addLineItem(LineItem lineItem) throws SQLException {
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareCall(SQLCommand.LINEITEM_QUERY_ADD);
	    preparedStatement.setInt(1, lineItem.getOrderId());
	    preparedStatement.setInt(2, lineItem.getProductId());
	    preparedStatement.setInt(3, lineItem.getQuantity());
	    preparedStatement.setDouble(4, lineItem.getPrice());
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
    public boolean updateLineItem(int orderId, int productId, LineItem lineItem) throws SQLException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean deleteLineItem(int orderId, int productId) throws SQLException {
	// TODO Auto-generated method stub
	return false;
    }

}