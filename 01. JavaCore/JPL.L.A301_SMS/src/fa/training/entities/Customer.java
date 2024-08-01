package fa.training.entities;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int customerId;
    private String customerName;

    public Customer() {
    }

    public Customer(int customerId, String customerName) {
	super();
	this.customerId = customerId;
	this.customerName = customerName;
    }

    public int getCustomerId() {
	return customerId;
    }

    public void setCustomerId(String customerId) throws NumberFormatException {
	this.customerId = Integer.parseInt(customerId);
    }
    
    public void setCustomerId(int customerId){
	this.customerId = customerId;
    }
    
    public String getCustomerName() {
	return customerName;
    }

    public void setCustomerName(String customerName) {
	this.customerName = customerName;
    }

}