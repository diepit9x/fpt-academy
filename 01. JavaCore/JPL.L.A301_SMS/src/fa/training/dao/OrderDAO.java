package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.entities.Order;

public interface OrderDAO {
    /**
     * Get all order of customer
     * @param customerId
     * @return
     * @throws SQLException
     */
    List<Order> getAllOrdersByCustomerId(int customerId) throws SQLException;
    
    /**
     * find an order
     * @param orderId
     * @return
     * @throws SQLException
     */
    Order findOrderById(int orderId) throws SQLException;
    
    /**
     * Update order total
     * @param orderId
     * @return
     * @throws SQLException
     */
    boolean updateOrderTotal(int orderId) throws SQLException;
    
    /**
     * Add an order
     * @param order
     * @return
     * @throws SQLException
     */
    boolean addOrder(Order order) throws SQLException;
    
    /**
     * Update an order
     * @param order
     * @return
     * @throws SQLException
     */
    boolean updateOrder(Order order) throws SQLException;
    
    /**
     * delete an order bu orderId
     * @param orderId
     * @return
     * @throws SQLException
     */
    boolean deleteOrder(int orderId) throws SQLException;
    
}
