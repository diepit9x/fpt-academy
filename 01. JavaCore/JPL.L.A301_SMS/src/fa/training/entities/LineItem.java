package fa.training.entities;

import java.io.Serializable;

public class LineItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderId;
    private int productId;
    private int quantity;
    private double price;

    public LineItem() {
    }

    public LineItem(int orderId, int productId, int quantity, double price) {
	super();
	this.orderId = orderId;
	this.productId = productId;
	this.quantity = quantity;
	this.price = price;
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

    public int getProductId() {
	return productId;
    }

    public void setProductId(String productId) throws NumberFormatException {
	this.productId = Integer.parseInt(productId);
    }
    
    public void setProductId(int productId) {
	this.productId = productId;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(String quantity) throws NumberFormatException {
	this.quantity = Integer.parseInt(quantity);
    }
    
    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(String price) throws NumberFormatException {
	this.price = Double.parseDouble(price);
    }
    
    public void setPrice(double price) {
	this.price = price;
    }

}
