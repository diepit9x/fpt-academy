package fa.training.entities;

import java.util.Scanner;

public class Video extends Multimedia {

    public Video() {
	super();
    }

    public Video(String name, double duration) {
	super(name, duration);
    }
    
    public void createVideo(Scanner scanner) {
	System.out.println("----Enter video information----");
	super.createMultimedia(scanner);
    }

    @Override
    public String toString() {
	return String.format("%-15s %-15s %-10.1f",
                "Video:",  getName() , getDuration());
    }
}
