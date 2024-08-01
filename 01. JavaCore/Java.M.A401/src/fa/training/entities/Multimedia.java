package fa.training.entities;

import java.util.Scanner;

public abstract class Multimedia {
    private String name;
    private double duration; // duration in minutes

    public Multimedia() {
    }

    public Multimedia(String name, double duration) {
	this.name = name;
	this.duration = duration;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getDuration() {
	return duration;
    }

    public void setDuration(double duration) {
	this.duration = duration;
    }

    public void createMultimedia(Scanner scanner) {
	System.out.print("Enter name: ");
	this.name = scanner.nextLine();
	System.out.print("Enter duration: ");
	while (true) {
	    try {
		this.duration = Double.parseDouble(scanner.nextLine());
		if (this.duration < 0) {
		    throw new NumberFormatException();
		}
		break;
	    } catch (NumberFormatException e) {
		System.out.print("Invalid input. Please try again: ");
	    }
	}
    }
}

