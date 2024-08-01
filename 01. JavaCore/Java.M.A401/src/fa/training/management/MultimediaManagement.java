package fa.training.management;

import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Multimedia;

public class MultimediaManagement {
    List<Multimedia> listOfMultimedia;

    public MultimediaManagement() {
	this.listOfMultimedia = new ArrayList<Multimedia>();
    }
    
    public void addMultiMedia(Multimedia multimedia) {
	this.listOfMultimedia.add(multimedia);
    }
    
    public void display() {
	System.out.println("-----LIST OF MULTIMEDIA-----");
	for (Multimedia multimedia : listOfMultimedia) {
	    System.out.println(multimedia.toString());
	}
    }
}
