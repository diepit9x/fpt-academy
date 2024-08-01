package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.MovieType;
import fa.training.entities.MovieTypeId;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface MovieTypeDAO {
	MovieType getMovieTypeByID(MovieTypeId movieTypeId) throws HibernateException;
	List<MovieType> getAllMovieTypes() throws HibernateException;
	boolean updateMovieTypeByID(MovieTypeId movieTypeId, MovieType movieType) throws HibernateException, DataNotFoundException;
	boolean deleteMovieTypeByID(MovieTypeId movieTypeId) throws HibernateException, DataNotFoundException;
	boolean insertMovieType(MovieType movieType) throws HibernateException, DataAlreadyExistException;
}
