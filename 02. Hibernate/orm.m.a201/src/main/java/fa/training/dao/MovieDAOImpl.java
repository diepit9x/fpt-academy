package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Movie;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;

public class MovieDAOImpl implements MovieDAO {

	@Override
	public Movie getMovieByID(String movieId) throws HibernateException {
		if (movieId == null) {
			return null;
		}
		Movie movie = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			movie = session.get(Movie.class, movieId);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return movie;
	}

	@Override
	public List<Movie> getAllMovies() throws HibernateException {
		List<Movie> movies = new ArrayList<>();
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			Query<Movie> query = session.createQuery("FROM Movie", Movie.class);
			movies = query.list();
			
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return movies;
	}

	@Override
	public boolean updateMovieByID(String movieId, Movie movie) throws HibernateException, DataNotFoundException {
		if (movieId == null || movie == null) {
			return false;
		}
		boolean status =false;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Movie existingMovie = getMovieByID(movieId);
			if (existingMovie == null) {
				throw new DataNotFoundException();
			}
			//update movie id
			movie.setMovieId(movieId);
			session.merge(movie);
			status = true;
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean deleteMovieByID(String movieId) throws HibernateException, DataNotFoundException {
		if (movieId == null) {
			return false;
		}
		boolean status =false;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Movie existingMovie = getMovieByID(movieId);
			if (existingMovie == null) {
				throw new DataNotFoundException();
			}
			session.remove(existingMovie);
			status = true;
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean insertMovie(Movie movie) throws HibernateException, DataAlreadyExistException {
		if (movie == null) {
			return false;
		}
		boolean status =false;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			if (movie.getMovieId() != null) {
				Movie existingMovie = getMovieByID(movie.getMovieId());
				if (existingMovie != null) {
					throw new DataAlreadyExistException();
				}
			}
			session.merge(movie);
			status = true;
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

}
