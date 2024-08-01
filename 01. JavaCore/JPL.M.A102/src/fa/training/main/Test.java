package fa.training.main;

import fa.training.entities.Inventory;
import fa.training.threads.InventoryTask;

public class Test {
    public static void main(String[] args) {
	Inventory inventory = new Inventory(500);
	
	Runnable task1 = new InventoryTask(inventory, 13);
	Runnable task2 = new InventoryTask(inventory, 91);
	Runnable task3 = new InventoryTask(inventory, 50);
	Thread thread1 = new Thread(task1);
	Thread thread2 = new Thread(task2);
	Thread thread3 = new Thread(task3);
	thread1.start();
	thread2.start();
	thread3.start();
	
    }

}
