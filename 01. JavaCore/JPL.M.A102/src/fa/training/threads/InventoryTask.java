package fa.training.threads;

import fa.training.entities.Inventory;

public class InventoryTask implements Runnable{
    private Inventory inventory;
    private int order;
    
    public InventoryTask(Inventory inventory, int order) {
	this.inventory = inventory;
	this.order = order;
    }

    @Override
    public void run() {
	inventory.order(this.order);
    }

}
