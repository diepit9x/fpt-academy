package fa.training.service;

import java.sql.SQLException;
import java.util.List;

import fa.training.dao.LineItemDAO;
import fa.training.dao.LineItemDAOImpl;
import fa.training.entities.LineItem;
import fa.training.exception.DataNotFoundException;
import fa.training.util.Constant;

public class LineItemService {
    private LineItemDAO lineItemDAO = new LineItemDAOImpl();
    
    /**
     * Get all Items by orderid
     * @param orderId
     * @throws SQLException
     * @throws DataNotFoundException
     */
    public void getAllItemsByOrderId(int orderId)  throws SQLException, DataNotFoundException {
	List<LineItem> lineItems = lineItemDAO.getAllItemsByOrderId(orderId);
	if (lineItems.isEmpty()) {
	    throw new DataNotFoundException(Constant.LINEITEM_LIST_IS_EMPTY);
	}
	System.out.println(String.format("%-20s %-20s %-20s %s","ORDER ID", "PRODUCT ID", "QUANTITY", "PRICE"));
	System.out.println("---------------------------------------------------------------------------");
	for (LineItem lineItem : lineItems) {
	    String stringFormat = String.format("%-30d %-35d %-20d %.2f",
		    lineItem.getOrderId(),
		    lineItem.getProductId(),
		    lineItem.getQuantity(),
		    lineItem.getPrice()
		    );
	    System.out.println(stringFormat);
	}
    }
    
    /**
     * Compute order total
     * @param orderId
     * @throws SQLException
     */
    public void computeOrderTotal(int orderId) throws SQLException {
	double total = lineItemDAO.computeOrderTotal(orderId);
	System.out.println("price total of orderid="+orderId+": "+total);
    }
    
    /**
     * Add a new LineItem
     * @param lineItem
     * @throws SQLException
     */
    public void addLineItem(LineItem lineItem) throws SQLException {
	boolean status = lineItemDAO.addLineItem(lineItem);
	if (status) {
	    System.out.println("Add a new LineItem successfully");
	} else {
	    System.out.println("Add failure");
	}
    }
    
    public static void main(String[] args) {
	LineItemService lineItemService = new LineItemService();
	
	try {
//	    lineItemService.getAllItemsByOrderId(5);
	    lineItemService.computeOrderTotal(5);

//	    LineItem lineItem = new LineItem(5,3,5,100);
//	    LineItem lineItem2 = new LineItem(5,1,5,100);
//	    
//	    lineItemService.addLineItem(lineItem);
//	    lineItemService.addLineItem(lineItem2);
	    
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    System.err.println(e.getMessage());
	} 
    }
}
