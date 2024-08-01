package fa.training.entities;

import java.util.Scanner;

public class Song extends Multimedia {
    private String singer;

    public Song() {
	super();
    }

    public Song(String name, double duration, String singer) {
	super(name, duration);
	this.singer = singer;
    }
    
    public void createSong(Scanner scanner) {
	System.out.println("----Enter song information----");
	super.createMultimedia(scanner);
	System.out.print("Enter singer: ");
	this.singer = scanner.nextLine();
    }

    @Override
    public String toString() {
	return String.format("%-15s %-15s %-10.1f %s",
                "Song:",  getName() , getDuration(), singer);
    }
}
