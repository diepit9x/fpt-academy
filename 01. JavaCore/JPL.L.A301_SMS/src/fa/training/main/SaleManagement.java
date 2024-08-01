package fa.training.main;

import java.util.Date;
import java.util.Scanner;

import fa.training.entities.Customer;
import fa.training.entities.LineItem;
import fa.training.entities.Order;
import fa.training.service.CustomerService;
import fa.training.service.LineItemService;
import fa.training.service.OrderService;

public class SaleManagement {
    
    public static void main(String[] args) {
	CustomerService customerService = new CustomerService();
	OrderService orderService = new OrderService();
	LineItemService lineItemService = new LineItemService();
	Scanner scanner = new Scanner(System.in);
	
	try {
//	    List all customers consist of customer id, customer name in the database
	    customerService.getAllCustomer();
	    scanner.nextLine();
	    
//	    List all orders consist of order id, order date, customer id, employee id, total for a customer
	    int customerId = 4;
	    orderService.getAllOrdersByCustomerId(customerId);
	    scanner.nextLine();
	    
//	    List all lineitems for an order
	    int orderId = 8;
	    lineItemService.getAllItemsByOrderId(orderId);
	    scanner.nextLine();
	    
//	    Compute order total
	    int corderId = 8;
	    lineItemService.computeOrderTotal(corderId);
	    scanner.nextLine();
	    
//	    Add a customer into the database
	    Customer newCustomer = new Customer();
	    newCustomer.setCustomerName("new customer");
	    //customerService.addCustomer(newCustomer);
	    scanner.nextLine();
	    
//	    Delete a customer from the database
	    int customeridDel = 3;
	    //customerService.deleteCustomer(customeridDel);
	    scanner.nextLine();
	    
//	    Update a customer in the database
	    Customer customerUpdate = new Customer();
	    customerUpdate.setCustomerId(7); //select an id
	    customerUpdate.setCustomerName("update name"); //update
	    //customerService.updateCustomer(customerUpdate);
	    scanner.nextLine();
	    
//	    Create an order into the database 
	    Order newOrder = new Order();
	    newOrder.setOrderDate(new Date(2020,10,20));
	    newOrder.setCustomerId(7);
	    newOrder.setEmployeeId(1);
	    newOrder.setTotal(0);
	    //orderService.addOrder(newOrder);
	    scanner.nextLine();
	    
//	    Create a lineitem into the database
	    LineItem newLineItem = new LineItem();
	    newLineItem.setOrderId(10);
	    newLineItem.setProductId(3);
	    newLineItem.setQuantity(1);
	    newLineItem.setPrice(50);
	    //lineItemService.addLineItem(newLineItem);
	    scanner.nextLine();
	    
//	    Update an order total into the database
	    int orderIdUpdate = 8;
	    orderService.updateOrderTotal(orderIdUpdate);
	    scanner.nextLine();
	    
	} catch (Exception e) {
	    System.err.println(e.getMessage());
	}

    }

}
