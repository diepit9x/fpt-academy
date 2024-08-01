package baitap2.yeucau2;

public class ArrayTask implements Runnable {
    Sum sum;
    int[] arr;
    public  ArrayTask(Sum sum, int[] arr) {
	this.sum = sum;
	this.arr = arr;
    }

    @Override
    public void run() {
	sum.caculator(arr);
    }

}
