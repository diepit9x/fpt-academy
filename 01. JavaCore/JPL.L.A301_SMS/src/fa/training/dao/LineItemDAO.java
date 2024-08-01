package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.entities.LineItem;

public interface LineItemDAO {
    /**
     * Get all Itemline by orderId
     * @param orderId
     * @return
     * @throws SQLException
     */
    List<LineItem> getAllItemsByOrderId(int orderId) throws SQLException;
    
    /**
     * Compute total price
     * @param orderId
     * @return
     * @throws SQLException
     */
    double computeOrderTotal(int orderId) throws SQLException;
    
    /**
     * Add an itemline
     * @param lineItem
     * @return
     * @throws SQLException
     */
    boolean addLineItem(LineItem lineItem) throws SQLException;
    
    /**
     * update an itemline
     * @param orderId
     * @param productId
     * @param lineItem
     * @return
     * @throws SQLException
     */
    boolean updateLineItem(int orderId, int productId, LineItem lineItem) throws SQLException;
    
    /**
     * Delete an itemline
     * @param orderId
     * @param productId
     * @return
     * @throws SQLException
     */
    boolean deleteLineItem(int orderId, int productId) throws SQLException;
}