package baitap2.yeucau1;

public class Test {
    public static void main(String[] args) {
	CreateArray createArray = new CreateArray();
	int[] arr = createArray.getArr();
	long beginTime = 0;
	long endTime = 0;
	long sum = 0;

	System.out.println("====SINGLE THREAD====");
	beginTime = System.currentTimeMillis();
	for (int i = 0; i < arr.length; i++) {
	    sum += arr[i];
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	endTime = System.currentTimeMillis();
	System.out.println("S = " + sum);
	System.out.println("Time = " + (endTime - beginTime));
	System.out.println("======END=====");

	
	System.out.println("****TWO THREADS****");
	LeftSumThread task1 = new LeftSumThread(arr);
	RightSumThread task2 = new RightSumThread(arr);
	Thread thread1 = new Thread(task1);
	Thread thread2 = new Thread(task2);
	beginTime = System.currentTimeMillis();
	thread1.start();
	thread2.start();
	try {
	    thread1.join();
	    thread2.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	sum = task1.getSum() + task2.getSum();
	endTime = System.currentTimeMillis();

	System.out.println("S = " + sum);
	System.out.println("Time = " + (endTime - beginTime));
	System.out.println("*****END*****");

    }

}