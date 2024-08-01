package baitap2.yeucau1;

import java.util.Random;

public class CreateArray {
    private int[] arr = new int[10];

//    private int[] arr = new int[1000000];

    public CreateArray() {
	Random random = new Random();
	for (int i = 0; i < arr.length; i++) {
	    arr[i] = random.nextInt(100);
	}
    }

    public int[] getArr() {
        return arr;
    }

}
