package fa.training.services;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import fa.training.entities.Book;
import fa.training.entities.Publication;
import fa.training.utils.Validator;

public class BookService extends PublicationService {
    
    public BookService() {
    }
    
    /**
     * add a book to a collection
     * @param book
     * @return
     */
    public void addBook(List<Publication> publications, Scanner scanner) {
	try {
	    Book book = input(publications, scanner);
	    publications.add(book);
	    System.out.println("Add a new book successfully");
	} catch (Exception e) {
	   e.printStackTrace();
	}
    }
    
    /**
     * add an author  to a book 
     * @param book
     * @param author
     * @return
     */
    public void addAuthor(List<Publication> publications, Book book ,String author) {
	int index = publications.indexOf(book);
	if (index != -1) {
	    boolean add = book.addAuthor(author);
	    if (add == true) {
		publications.set(index, book);
		System.out.println("Add successfully");
	    } else {
		System.err.println("Author existed");
	    }
	    
	} else {
	    System.err.println("This book does not exist");
	}
    }
    
    /**
     * search book by isbn
     * @param isbn
     * @return
     */
    public List<Publication> searchByIsbn(List<Publication> publications, String isbn) {
	List<Publication> resultList = publications.stream()
		.filter(publication -> publication instanceof Book)
		.map(Book.class::cast)
		.filter(book -> book.getIsbn().equals(isbn))
		.sorted(Comparator.comparing(Book::getIsbn)
			.thenComparing(Book::getPublicationDate)
			)
		.collect(Collectors.toList());
	return resultList;
    }
    
    /**
     * Search books by author
     * @param author
     * @return
     */
    public List<Publication> searchByAuthor(List<Publication> publications, String author) {
	List<Publication> resultList = publications.stream()
		.filter(publication -> publication instanceof Book)
		.map(Book.class::cast)
		.filter(book -> book.getAuthors().contains(author))
		.sorted(Comparator.comparing(Book::getIsbn)
			.thenComparing(Book::getPublicationDate)
			)
		.collect(Collectors.toList());
	return resultList;
    }
    
    /**
     * Search books by publisher
     * @param publisher
     * @return
     */
    public List<Publication> searchByPublisher(List<Publication> publications, String publisher) {
	List<Publication> resultList = publications.stream()
		.filter(publication -> publication instanceof Book)
		.map(Book.class::cast)
		.filter(book -> book.getPublisher().equals(publisher))
		.sorted(Comparator.comparing(Book::getIsbn)
			.thenComparing(Book::getPublicationDate)
			)
		.collect(Collectors.toList());
	return resultList;
    }
    
    /**
     * input data
     * @param scanner
     * @return
     */
    public Book input(List<Publication> publications, Scanner scanner) {
	int  publicationYear;
	Date publicationDate;
	String publisher, isbn, publicationPlace;
	Set<String> authors = new HashSet<>();
	
	
	System.out.print("publicationYear: ");
	do {
	    try {
		publicationYear = Integer.parseInt(scanner.nextLine());
		break;
	    } catch (Exception e) {
		e.printStackTrace();
		System.err.print("Try again:");
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

	System.out.print("isbn: ");
	do {
	    isbn = scanner.nextLine();
	    if (Validator.isIsbn(isbn)) {
		if (searchByIsbn(publications, isbn).isEmpty()) {
		    break;
		} else {
		    System.out.print("This isbn existed. Try again: ");
		    continue;
		}
	    }
	    System.err.print("This isbn is invalid. Try again: ");
	} while (true);
	
	System.out.print("publicationPlace: ");
	publicationPlace = scanner.nextLine();
	
	String author;
	do {
	    System.out.print("author: ");
	    author = scanner.nextLine();
	    if (authors.contains(author)) {
		System.err.println("This author existed. Try again");
	    } else {
		authors.add(author);
		System.out.print("Do you want to add more author ? (Y/N): ");
		if (scanner.nextLine().toUpperCase().equals("N")) {
		    break;
		}
	    }
	} while (true);
	
	Book inputBook = new Book();
	inputBook.setPublicationYear(publicationYear);
	inputBook.setPublisher(publisher);
	inputBook.setPublicationDate(publicationDate);
	inputBook.setIsbn(isbn);
	inputBook.setAuthors(authors);
	inputBook.setPublicationPlace(publicationPlace);
	
	return inputBook;
    }

}
