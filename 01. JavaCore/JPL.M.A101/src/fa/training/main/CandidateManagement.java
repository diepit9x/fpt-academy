package fa.training.main;

import java.util.List;
import java.util.Scanner;

import fa.training.services.CandidateService;

public class CandidateManagement {

    public static void main(String[] args) throws InterruptedException {
	CandidateService candidateService = new CandidateService();
	System.out.println("-----------");
	// add candidate thread
	Runnable task1 = new Runnable() {
	    private CandidateService cService = candidateService;

	    @Override
	    public void run() {
		cService.addCandidate(new Scanner(System.in));
	    }
	};

	// read candidate thread
	Runnable task2 = new Runnable() {
	    private CandidateService cService = candidateService;

	    @Override
	    public void run() {
		try {
		    Thread.sleep(500);
		    List<String> candidates = cService.readFile();
		    System.out.println("----read file-----");
		    for (String candidate : candidates) {
			System.out.println(candidate);
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	};

	// read candidate thread
	Runnable task3 = new Runnable() {
	    private CandidateService cService = candidateService;

	    @Override
	    public void run() {
		try {
		    Thread.sleep(500);
		    cService.displayCandidate();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	};

	Thread thread1 = new Thread(task1);
	Thread thread2 = new Thread(task2);
	Thread thread3 = new Thread(task3);

	thread1.start();
	thread2.start();
	thread3.start();

	try {
	    thread1.join();
	    thread2.join();
	    thread3.join();
	} catch (Exception e) {
	   e.printStackTrace();
	}

    }

}