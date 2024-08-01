package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Movie;
import fa.training.entities.MovieType;
import fa.training.entities.MovieTypeId;
import fa.training.entities.Type;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;

public class MovieTypeDAOImpl implements MovieTypeDAO {

	@Override
	public MovieType getMovieTypeByID(MovieTypeId movieTypeId) throws HibernateException {
		if (movieTypeId.getMovie() == null || movieTypeId.getType() == null) {
			return null;
		}
		MovieType movieType = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			movieType = session.get(MovieType.class, movieTypeId);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return movieType;
	}

	@Override
	public List<MovieType> getAllMovieTypes() throws HibernateException {
		List<MovieType> movieTypes = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<MovieType> query = session.createQuery("FROM MovieType", MovieType.class);
			movieTypes = query.list();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return movieTypes;
	}

	@Override
	public boolean updateMovieTypeByID(MovieTypeId movieTypeId, MovieType movieType)
			throws HibernateException, DataNotFoundException {
		if (movieTypeId.getMovie() == null || movieTypeId.getType() == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();

			MovieType existingMovieType = getMovieTypeByID(movieTypeId);
			if (existingMovieType == null) {
				throw new DataNotFoundException();
			}
			// update
			existingMovieType.setMtDescription(movieType.getMtDescription());
			session.merge(existingMovieType);
			status = true;
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean deleteMovieTypeByID(MovieTypeId movieTypeId)
			throws HibernateException, DataNotFoundException {
		if (movieTypeId.getMovie() == null || movieTypeId.getType() == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();

			MovieType existingMovieType = getMovieTypeByID(movieTypeId);
			if (existingMovieType == null) {
				throw new DataNotFoundException();
			}
			session.remove(existingMovieType);
			status = true;

			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean insertMovieType(MovieType movieType) throws HibernateException, DataAlreadyExistException {
	    if (movieType == null) {
	        return false;
	    }
	    boolean status = false;
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        Type type = movieType.getMovieTypeId().getType();
	        Type existingType = session.get(Type.class, type.getTypeId());
	        if (existingType == null) {
	            session.save(type);
	            movieType.getMovieTypeId().setType(type);
	        } else {
	            movieType.getMovieTypeId().setType(existingType);
	        }
	        
	        Movie movie = movieType.getMovieTypeId().getMovie();
	        Movie existingMovie = session.get(Movie.class, movie.getMovieId());
	        if (existingMovie == null) {
				session.save(movie);
			} else {
				movieType.getMovieTypeId().setMovie(existingMovie);
			}
	        
	        MovieType existingMovieType = getMovieTypeByID(movieType.getMovieTypeId());
	        if (existingMovieType != null) {
	            throw new DataAlreadyExistException();
	        }
	        session.save(movieType);
	    
	        transaction.commit();
	        status = true;
	    } catch (HibernateException e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        System.err.println("Error: " + e.getMessage());
	    }
	    return status;
	}



}
