package fa.training.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fa.training.util.Validator;

public class Order {
    private int orderId;
    private Date orderDate;
    private int customerId;
    private int employeeId;
    private double total;

    public Order() {
    }

    public Order(int orderId, Date orderDate, int customerId, int employeeId, double total) {
	super();
	this.orderId = orderId;
	this.orderDate = orderDate;
	this.customerId = customerId;
	this.employeeId = employeeId;
	this.total = total;
    }

    public int getOrderId() {
	return orderId;
    }

    public void setOrderId(String orderId) throws NumberFormatException {
	this.orderId = Integer.parseInt(orderId);
    }

    public Date getOrderDate() {
	return orderDate;
    }

    public void setOrderDate(String orderDate) throws ParseException {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	try {
	    orderDate = dateFormat.format(dateFormat.parse(orderDate));
	    if (Validator.isDate(orderDate)) {
		this.orderDate = dateFormat.parse(orderDate);
	    }
	} catch (ParseException e) {
	    throw e;
	}
    }

    public int getCustomerId() {
	return customerId;
    }

    public void setCustomerId(String customerId) throws NumberFormatException {
	this.customerId = Integer.parseInt(customerId);
    }

    public int getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(String employeeId) throws NumberFormatException {
	this.employeeId = Integer.parseInt(employeeId);
    }

    public double getTotal() {
	return total;
    }

    public void setTotal(String total) throws NumberFormatException {
	this.total = Double.parseDouble(total);
    }

}
