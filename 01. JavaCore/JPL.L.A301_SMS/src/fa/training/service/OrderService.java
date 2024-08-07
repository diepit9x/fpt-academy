package fa.training.service;

import java.sql.SQLException;
import java.util.List;

import fa.training.dao.OrderDAO;
import fa.training.dao.OrderDAOImpl;
import fa.training.entities.Order;
import fa.training.exception.DataNotFoundException;
import fa.training.util.Constant;

public class OrderService {
    OrderDAO orderDAO = new OrderDAOImpl();
    
    /**
     * get all orders by customerId
     * @param customerId
     * @throws SQLException
     * @throws DataNotFoundException
     */
    public void getAllOrdersByCustomerId(int customerId) throws SQLException, DataNotFoundException{
	List<Order> orders = orderDAO.getAllOrdersByCustomerId(customerId);
	if (orders.isEmpty()) {
	    throw new DataNotFoundException(Constant.ORDER_LIST_IS_EMPTY);
	}
	System.out.println(String.format("%-20s %-20s %-20s %-20s  %s", "ORDER ID", "ORDER DATE", "CUSTOMER ID", "EMPLOYEE ID", "TOTAL"));
	System.out.println("------------------------------------------------------------------------------------------------------------");
	for (Order order : orders) {
	    String stringFormat = String.format("%-30s %-30s %-30s %-25s  %s",
		    order.getOrderId(),
		    order.getOrderDate(),
		    order.getCustomerId(),
		    order.getEmployeeId(),
		    order.getTotal() );
	    System.out.println(stringFormat);
	}
    }
    
    /**
     * Add a new order
     * @param order
     * @throws SQLException
     */
    public void addOrder(Order order) throws SQLException {
	boolean status = orderDAO.addOrder(order);
	if (status) {
	    System.out.println("Add a new order successfully");
	}
    }
    
    public void updateOrderTotal(int orderId) throws SQLException {
	boolean status = orderDAO.updateOrderTotal(orderId);
	if (status) {
	    System.out.println("Update total price successfully");
	}
    }
    
    public static void main(String[] args) {
	try {
	    OrderService orderService = new OrderService();
//	    orderService.getAllOrdersByCustomerId(3);
//	    Order order = new Order(1, new Date(2020,10,10), 3, 1, 500);
//	    Order order2 = new Order(1, new Date(2020,10,13), 4, 1, 5000);
//	    orderService.addOrder(order);
//	    orderService.addOrder(order2);
	    orderService.updateOrderTotal(5);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
