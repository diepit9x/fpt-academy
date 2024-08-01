package fa.training.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.dao.MovieDAO;
import fa.training.dao.MovieDAOImpl;
import fa.training.dao.MovieTypeDAO;
import fa.training.dao.MovieTypeDAOImpl;
import fa.training.dao.TypeDAO;
import fa.training.dao.TypeDAOImpl;
import fa.training.entities.Movie;
import fa.training.entities.MovieType;
import fa.training.entities.MovieTypeId;
import fa.training.entities.Type;

public class MovieTypeDAOTest {
	private static MovieTypeDAO movieTypeDAO;
	private static MovieDAO movieDAO;
	private static TypeDAO typeDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		movieTypeDAO = new MovieTypeDAOImpl();
		movieDAO = new MovieDAOImpl();
		typeDAO = new TypeDAOImpl();
	}
	
	@Test
	public void testGetById() {
		Movie movie = movieDAO.getMovieByID("1");
		assertNotNull(movie);
		Type type = typeDAO.getTypeByID(1);
		assertNotNull(type);
		
		MovieTypeId movieTypeId = new MovieTypeId(type, movie);
		
		MovieType movieType =  movieTypeDAO.getMovieTypeByID(movieTypeId);
		assertNotNull(movieType);
		
	}
	
	@Test
	public void testGetAll() throws Exception {
		List<MovieType> movieTypes = movieTypeDAO.getAllMovieTypes();
		assertNotNull(movieTypes);
		assertFalse(movieTypes.isEmpty());
	}
	
	@Test
	public void testUpdate() throws Exception {
		Movie movie = movieDAO.getMovieByID("1");
		assertNotNull(movie);
		Type type = typeDAO.getTypeByID(1);
		assertNotNull(type);
		
		MovieTypeId movieTypeId = new MovieTypeId(type, movie);
		MovieType movieType =  movieTypeDAO.getMovieTypeByID(movieTypeId);
		assertNotNull(movieType);
		
		String updateData = "update "+LocalDateTime.now();
		movieType.setMtDescription(updateData);
		boolean status =  movieTypeDAO.updateMovieTypeByID(movieTypeId, movieType);
		assertTrue(status);
		
		movieType =  movieTypeDAO.getMovieTypeByID(movieTypeId);
        assertEquals(updateData, movieType.getMtDescription());
	}
	
	@Test
	public void testInsert() throws Exception {
		Movie newMovie = new Movie();
        newMovie.setMovieId("M123456");
        newMovie.setActor("new actor");
        newMovie.setContent("new content");
        newMovie.setDirector("new director");
        newMovie.setMovieNameEng("eng13434");
        newMovie.setMovieNameVn("vn13434");
        
        Type newType = new Type();
		newType.setTypeDescription("new234244");
		newType.setTypeName("new " + LocalDateTime.now());

		MovieTypeId movieTypeId = new MovieTypeId(newType, newMovie);
		
//		
//		Movie movie = movieDAO.getMovieByID("1");
//		assertNotNull(movie);
//		Type type = typeDAO.getTypeByID(20);
//		assertNotNull(type);
//		
//		MovieTypeId movieTypeId = new MovieTypeId(type, movie);
		
		
		MovieType newMovieType = new MovieType();
		newMovieType.setMovieTypeId(movieTypeId);
		newMovieType.setMtDescription("new dcdfsdfsdfsdf");
		
		boolean status = movieTypeDAO.insertMovieType(newMovieType);
		assertTrue(status);
	}
	
	@Test
	public void testDelete() throws Exception {
		Movie movie = movieDAO.getMovieByID("M12345");
		assertNotNull(movie);
		Type type = typeDAO.getTypeByID(28);
		assertNotNull(type);
		
		MovieTypeId movieTypeId = new MovieTypeId(type, movie);
		boolean status = movieTypeDAO.deleteMovieTypeByID(movieTypeId);
		assertTrue(status);
	}
}
