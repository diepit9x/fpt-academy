package fa.training.services;

import java.util.Comparator;
import java.util.List;

import fa.training.entities.Publication;

public abstract class PublicationService {

    /**
     * Display the list of all books and magazines that have the same publication year and publisher.
     * @param publications
     */
    public void displayAll(List<Publication> publications) {
	List<Publication> sortedList = publications.stream()
		.sorted(Comparator.comparing(Publication::getPublicationYear)
			.thenComparing(Publication::getPublisher))
		.toList();
	for (Publication publication : sortedList) {
	    publication.display();
	}
    }

}
