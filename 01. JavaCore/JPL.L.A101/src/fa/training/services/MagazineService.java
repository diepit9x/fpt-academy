package fa.training.services;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import fa.training.entities.Magazine;
import fa.training.entities.Publication;

public class MagazineService extends PublicationService {
    
    public MagazineService() {
    }
    
    /**
     * add a new magazine
     * @param publications
     * @param scanner
     */
    public void addMagazine(List<Publication> publications, Scanner scanner) {
	Magazine magazine = input(scanner);
	if (!publications.contains(magazine)) {
	    publications.add(magazine);
	    System.out.println("Add a new magazine successfully");
	}
	System.out.println("This magazine existed");
    }
    
    /**
     * display the list of top {limit} magazines which have the largest volume.
     * @param publications
     * @param limit
     */
    public void displayByVolume(List<Publication> publications, int limit) {
	for (Publication magazine : sortedByVolume(publications, limit)) {
	    System.out.println(magazine.toString());
	}
    }
    
    /**
     * Sort magazine by volume
     * @param publications
     * @param limit
     * @return
     */
    public List<Publication> sortedByVolume(List<Publication> publications, int limit) {
	return publications.stream()
		.map(Magazine.class::cast)
		.filter(publication -> publication instanceof Magazine)
		.sorted(Comparator.comparing(Magazine::getVolume).reversed())
		.limit(limit)
		.collect(Collectors.toList());
    }
    
    /**
     * input data
     * @param scanner
     * @return
     */
    public Magazine input(Scanner scanner) {
	int  publicationYear, volume, edition;
	Date publicationDate;
	String publisher, author;;
	
	
	System.out.print("publicationYear: ");
	do {
	    try {
		publicationYear = Integer.parseInt(scanner.nextLine());
		break;
	    } catch (Exception e) {
		e.printStackTrace();
		System.err.print("Invalid value. Try again:");
	    }
	} while (true);
	
	System.out.print("publisher: ");
	publisher = scanner.nextLine();
	
	System.out.print("publicationDate(dd/mm/yyyy): ");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	do {
	    try {
		publicationDate = dateFormat.parse(scanner.nextLine());
		break;
	    } catch (Exception e) {
		e.printStackTrace();
		System.err.print("Date is in valid. Try again: ");
	    }
	} while (true);
	
	System.out.print("author: ");
	author = scanner.nextLine();

	System.out.print("volume: ");
	do {
	    try {
		volume = Integer.parseInt(scanner.nextLine());
		break;
	    } catch (Exception e) {
		e.printStackTrace();
		System.err.print("Invalid value. Try again:");
	    }
	} while (true);
	
	System.out.print("edition: ");
	do {
	    try {
		edition = Integer.parseInt(scanner.nextLine());
		break;
	    } catch (Exception e) {
		e.printStackTrace();
		System.err.print("Invalid value. Try again:");
	    }
	} while (true);
	
	Magazine inputMagazine = new Magazine();
	inputMagazine.setPublicationYear(publicationYear);
	inputMagazine.setPublisher(publisher);
	inputMagazine.setPublicationDate(publicationDate);
	inputMagazine.setAuthor(author);
	inputMagazine.setVolume(volume);
	inputMagazine.setEdition(edition);
	
	return inputMagazine;
    }
    
}
