package fa.training.main;

import java.util.Scanner;

import fa.training.entities.Multimedia;
import fa.training.entities.Song;
import fa.training.entities.Video;
import fa.training.management.MultimediaManagement;

public class Test {
    public static void main(String[] args) {
	MultimediaManagement management = new MultimediaManagement();
	Scanner scanner = null;
	Multimedia multimedia;
	String choice;
	boolean exit = false;
	try {
	    scanner = new Scanner(System.in);
		while(true) {
		    menu();
		    choice = scanner.nextLine();
		    switch (choice) {
		    case "1":
			multimedia = new Video();
			((Video)multimedia).createVideo(scanner);
			management.addMultiMedia(multimedia);
			break;
		    case "2":
			multimedia= new Song();
			((Song)multimedia).createSong(scanner);
			management.addMultiMedia(multimedia);
			break;
		    case "3":
			management.display();
			break;
		    case "4":
			exit = true;
			break;
		    default:
			continue;
		    }
		    if (exit) {
			System.out.println("Exited");
			break;
		    }
		}
	} finally {
	    if (scanner != null) {
		scanner.close();
	    }
	}
    }
    
    public static void menu() {
	System.out.println("Choose function:");
	System.out.println("1. Add a new video");
	System.out.println("2. Add a new song");
	System.out.println("3. Show all multimedias");
	System.out.println("4. Exit");
	System.out.print("Your choice: ");

    }
}
