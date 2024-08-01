package fa.training.assignment2;

public class Manager extends Thread {
	private TaskList taskList;

	public Manager(TaskList taskList) {
		this.taskList = taskList;
	}

	@Override
	public void run() {
		int taskNumber = 1;
		while (true) {
			Task task = new Task("Task " + taskNumber);
			taskList.addTask(task);
			System.out.println("Manager added: " + task.getName());
			taskNumber++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
