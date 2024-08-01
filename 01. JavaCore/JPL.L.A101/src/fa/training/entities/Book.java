package fa.training.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Book extends Publication {
    private String isbn;
    private Set<String> authors = new HashSet<String>();
    private String publicationPlace;
    
    public Book() {
	super();
    }

    public Book(int publicationYear, String publisher, Date publicationDate, String isbn, Set<String> authors,
	    String publicationPlace) {
	super(publicationYear, publisher, publicationDate);
	this.isbn = isbn;
	this.authors = authors != null ? authors:this.authors;
	this.publicationPlace = publicationPlace;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public String getPublicationPlace() {
        return publicationPlace;
    }

    public boolean addAuthor(String author) {
	if (!authors.contains(author)) {
	    authors.add(author);
	    return true;
	}
	return false;
    }

    public void setPublicationPlace(String publicationPlace) {
        this.publicationPlace = publicationPlace;
    }
    
    @Override
    public void display() {
	System.out.println(toString());
    }

    @Override
    public String toString() {
	return "Book ["+super.toString()+", Isbn=" + getIsbn() + ", Authors=" + getAuthors() + ", PublicationPlace="
		+ getPublicationPlace()  + "]";
    }
}
