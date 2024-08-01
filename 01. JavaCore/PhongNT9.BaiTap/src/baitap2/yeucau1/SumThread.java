package baitap2.yeucau1;

public class SumThread implements Runnable{
    private int[] arrayInt;
    private long sum;

    public SumThread(int[] arrayInt) {
	this.arrayInt = arrayInt;
    }
    
    public long getSum() {
	return sum;
    }
    
    @Override
    public void run() {
	for (int i =  0; i < arrayInt.length; i++) {
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
