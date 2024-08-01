package fa.training.main;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Hibernate;

import fa.training.dao.MovieDAO;
import fa.training.dao.MovieDAOImpl;
import fa.training.entities.Movie;

/**
 * Hello world!
 *
 */
public class MovieApp 
{
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner(System.in);
    	MovieDAO movieDAO = new MovieDAOImpl();
    	
    	try {
//			System.out.println("---------SELECT ALL--------");
//	        List<Movie> movies = movieDAO.getAllMovies();
//	        for (Movie movie : movies) {
//				System.out.println(movie.toString());
//			}
//	        scanner.nextLine();
    		
	        System.out.println("---------SELECT By ID--------");
	        Movie existingMovie =  movieDAO.getMovieByID("1");
	        System.out.println(existingMovie.toString());
//	        scanner.nextLine();
	        
//	        System.out.println("---------update--------");
//	        Movie updateMovie = movieDAO.getMovieByID("1");
//	        updateMovie.setActor("update actor");
//	        movieDAO.updateMovieByID(updateMovie.getMovieId(), updateMovie);
//	        scanner.nextLine();
//	        
//	        
//	        System.out.println("---------insert--------");
//	        Movie newMovie = new Movie();
//	        newMovie.setMovieId("M123");
//	        newMovie.setActor("new actor");
//	        newMovie.setContent("new content");
//	        newMovie.setDirector("new director");
//	        newMovie.setMovieNameEng("eng");
//	        newMovie.setMovieNameVn("vn");
//	        movieDAO.insertMovie(newMovie);
//	        scanner.nextLine();
//	        
//	        
//	        System.out.println("---------delete--------");
//	        movieDAO.deleteMovieByID("M123");
//    		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

        

    }
}
