package fa.training.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fa.training.entities.Book;
import fa.training.entities.Publication;
import fa.training.services.BookService;
import fa.training.services.MagazineService;
import fa.training.utils.Constant;

public class LibraryManagement {
    private List<Publication> publications = new ArrayList<>();
	
    public static void main(String[] args) {
	LibraryManagement management = new LibraryManagement();
	BookService bookService = new BookService();
	MagazineService magazineService = new MagazineService();
	
	Scanner scanner = null;
	String choice, search;
	boolean exit = false;
	
	try {
	    scanner  = new Scanner(System.in);
	    do {
		menu();
		choice = scanner.nextLine();
		choice = choice.trim();
		switch (choice) {
		case Constant.ADD_BOOK:
		    System.out.println("----Add a book----");
		    bookService.addBook(management.publications, scanner);
		    break;
		case Constant.ADD_MAGAZINE:
		    System.out.println("----Add a magazine----");
		    magazineService.addMagazine(management.publications, scanner);
		    break;
		case Constant.DISPLAY_BOOK_MAGAZINE:
		    System.out.println("----Display books and magazines----");
		    magazineService.displayAll(management.publications);
		    
		    break;
		case Constant.ADD_AUTHOR:
		    management.addAuthorToBook(bookService, management.publications, scanner);
		    break;
		case Constant.DISPLAY_MAGAZINE:
		    System.out.println("----Display top 10 of magazines by volume----");
		    magazineService.displayByVolume(management.publications, 10);
		    break;
		case Constant.SEARCH_BOOK:
		    do {
			searchMenu();
			choice = scanner.nextLine();
			
			switch (choice) {
			case Constant.BY_ISBN:
			    System.out.print("isbn: ");
			    search = scanner.nextLine();
			    System.out.println(bookService.searchByIsbn(management.publications, search)); 
			    break;
			case Constant.BY_AUTHOR:
			    System.out.print("author: ");
			    search = scanner.nextLine();
			    System.out.println(bookService.searchByAuthor(management.publications, search)); 
			    break;
			case Constant.BY_PUBLISHER:
			    System.out.print("publisher: ");
			    search = scanner.nextLine();
			    System.out.println(bookService.searchByPublisher(management.publications, search)); 
			    break;
			case Constant.EXIT:
			    exit = true;
			    break;
			default:
			    System.err.println("Your choice is invalid");
			    continue;
			}
			if (exit) {
			    break;
			}
		    } while (true);
		    exit = false;
		    break;
		case Constant.EXIT:
		    exit = true;
		    break;
		default:
		    System.err.println("Your choice is invalid");
		    continue;
		}
		if (exit) {
		    break;
		}
		
	    } while (true);
	    
	} finally {
	    if (scanner != null) {
		scanner.close();
	    }
	}
    }
    
    /**
     * Add an author to a book into publications
     * @param bookService
     * @param publications
     * @param scanner
     */
    public void addAuthorToBook(BookService bookService, List<Publication> publications, Scanner scanner) {
	System.out.println("----Add author to books----");
	    System.out.print("book's isbn: ");
	    String isbn = scanner.nextLine();
	    List<Publication> books = bookService.searchByIsbn(publications, isbn);
	    if (books.isEmpty()) {
		System.err.println("This isbn does not exist");
	    } else {
		Book book = (Book)books.get(0);
		System.out.print("author: ");
		String author = scanner.nextLine();
		bookService.addAuthor(publications, book, author);
	    }
    }
    
    public static void menu() {
	System.out.println("====== LIBRARY MANAGEMENT SYSTEM ======");
	System.out.println("1. .Add a book");
	System.out.println("2. Add a magazine");
	System.out.println("3 .Display books and magazines");
	System.out.println("4. Add author to book");
	System.out.println("5. Display top 10 of magazines by volume");
	System.out.println("6. Search book by (isbn, author, publisher)");
	System.out.println("7. Exit");
	System.out.print("Choice: ");
	
    }
    
    public static void searchMenu() {
	System.out.println("----Search Book----");
	System.out.println("1. By isbn");
	System.out.println("2. By author");
	System.out.println("3. By publisher");
	System.out.println("7. Exit");
	System.out.print("Choice: ");
    }
    
}
