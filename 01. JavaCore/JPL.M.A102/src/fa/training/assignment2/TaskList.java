package fa.training.assignment2;

import java.util.LinkedList;
import java.util.List;

public class TaskList {
	private List<Task> tasks = new LinkedList<>();

	public synchronized void addTask(Task task) {
		tasks.add(task);
		notifyAll();
	}

	public synchronized Task getTask() {
		while (tasks.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		return tasks.remove(0);
	}
}
