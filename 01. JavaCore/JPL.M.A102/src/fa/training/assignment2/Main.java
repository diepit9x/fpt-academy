package fa.training.assignment2;

public class Main {
	public static void main(String[] args) {
		TaskList taskList = new TaskList();

		Manager manager = new Manager(taskList);
		manager.start();

		Worker worker1 = new Worker(taskList, "Worker 1");
		Worker worker2 = new Worker(taskList, "Worker 2");
		Worker worker3 = new Worker(taskList, "Worker 3");

		worker1.start();
		worker2.start();
		worker3.start();
	}
}
