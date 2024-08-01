package fa.training.entities;

public class Inventory {
    private int quantityOnHand = 0;
    private int ordered = 0;
    
    public Inventory(int quantityOnHand) {
	this.quantityOnHand = quantityOnHand;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }
    
    public synchronized void order(int quantityOrder) {
	if (quantityOrder > 0 && quantityOrder <= this.quantityOnHand) {
	    setOrdered(this.ordered + quantityOrder);
	    this.quantityOnHand -= quantityOrder;
	    System.out.println("Quantity ordered: " + quantityOrder);
	    System.out.println("Quantity on hand: " + this.quantityOnHand);
	    System.out.println("Total quantify taken away by way of order: " + this.ordered);
	} else {
	    System.err.println("Quantity order is invalid");
	}
    }

}
