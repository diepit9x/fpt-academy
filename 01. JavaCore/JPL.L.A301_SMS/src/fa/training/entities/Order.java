package fa.training.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fa.training.util.Validator;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
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
    
    public void setOrderId(int orderId) {
	this.orderId = orderId;
    }

    public Date getOrderDate() {
	return orderDate;
    }
    
    public java.sql.Date getOrderDateOfSqlDate() {
	return new java.sql.Date(orderDate.getTime());
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
    
    public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
    }

    public int getCustomerId() {
	return customerId;
    }

    public void setCustomerId(String customerId) throws NumberFormatException {
	this.customerId = Integer.parseInt(customerId);
    }
    
    public void setCustomerId(int customerId)  {
	this.customerId = customerId;
    }

    public int getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(String employeeId) throws NumberFormatException {
	this.employeeId = Integer.parseInt(employeeId);
    }
    
    public void  setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
    }

    public double getTotal() {
	return total;
    }

    public void setTotal(String total) throws NumberFormatException {
	this.total = Double.parseDouble(total);
    }
    
    public void setTotal(double total) {
	this.total = total;
    }
}