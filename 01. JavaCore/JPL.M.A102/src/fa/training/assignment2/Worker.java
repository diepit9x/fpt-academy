package fa.training.assignment2;

public class Worker extends Thread {
	private TaskList taskList;

	public Worker(TaskList taskList, String name) {
		super(name);
		this.taskList = taskList;
	}

	@Override
	public void run() {
		while (true) {
			Task task = taskList.getTask();
			System.out.println(Thread.currentThread().getName() + " is performing: " + task.getName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
