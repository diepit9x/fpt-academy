package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Movie;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface MovieDAO {
	Movie getMovieByID(String movieId) throws HibernateException;
	List<Movie> getAllMovies() throws HibernateException;
	boolean updateMovieByID(String movieId, Movie movie) throws HibernateException, DataNotFoundException;
	boolean deleteMovieByID(String movieId) throws HibernateException, DataNotFoundException;
	boolean insertMovie(Movie movie) throws HibernateException, DataAlreadyExistException;
}
