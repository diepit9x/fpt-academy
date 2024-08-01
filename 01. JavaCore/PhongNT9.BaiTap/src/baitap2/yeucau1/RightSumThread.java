package baitap2.yeucau1;

public class RightSumThread implements Runnable {
    private int[] arrayInt;
    private long sum;

    public RightSumThread(int[] arrayInt) {
	this.arrayInt = arrayInt;
    }
    
    public long getSum() {
	return sum;
    }
    
    @Override
    public void run() {
	for (int i =  arrayInt.length / 2; i < arrayInt.length; i++) {
	    this.sum += arrayInt[i];
	    
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	
    }
}
