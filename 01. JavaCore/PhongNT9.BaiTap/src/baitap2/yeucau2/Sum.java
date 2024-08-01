package baitap2.yeucau2;

public class Sum {
    private long sum = 0;

    public Sum() {
    }

    public long getSum() {
	return sum;
    }

    public void caculator(int[] arr) {
	synchronized (this) {
	    this.sum = 0;
		for (int i = 0; i < arr.length; i++) {
		    this.sum += arr[i];
		}
		System.out.println("KQ: " + this.sum);
	}
    }

}
