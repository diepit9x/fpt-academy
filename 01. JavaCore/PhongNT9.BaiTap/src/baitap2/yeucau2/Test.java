package baitap2.yeucau2;

public class Test {
    public static void main(String[] args) {
	Sum sum = new Sum();
	CreateArray createArray = new CreateArray();

	
	int[] arr = createArray.getArr();
	
	
	
	Runnable task = new ArrayTask(sum, arr);
	
	
	Thread thread1 = new Thread(task);
	Thread thread2 = new Thread(task);
	Thread thread3 = new Thread(task);
	Thread thread4 = new Thread(task);
	Thread thread5 = new Thread(task);
	Thread thread6 = new Thread(task);
	Thread thread7 = new Thread(task);
	
	thread1.start();
	thread2.start();
	thread3.start();
	thread4.start();
	thread5.start();
	thread6.start();
	thread7.start();
	
	try {
	    thread1.join();
	    thread2.join();
	    thread3.join();
	    thread4.join();
	    thread5.join();
	    thread6.join();
	    thread7.join();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
