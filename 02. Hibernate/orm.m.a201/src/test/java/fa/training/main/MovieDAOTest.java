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
import fa.training.entities.Movie;

public class MovieDAOTest {
	private static MovieDAO movieDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		movieDAO = new MovieDAOImpl();
	}
	
	@Test
	public void testGetById() {
		Movie movie = movieDAO.getMovieByID("1");
		assertNotNull(movie);
		assertEquals("1", movie.getMovieId());
	}
	
	@Test
	public void testGetAll() throws Exception {
		List<Movie> movies = movieDAO.getAllMovies();
		assertNotNull(movies);
		assertFalse(movies.isEmpty());
	}
	
	@Test
	public void testUpdate() throws Exception {
		Movie movie = movieDAO.getMovieByID("1");
		assertNotNull(movie);
		String updateData = "update "+LocalDateTime.now();
		movie.setContent(updateData);
        movieDAO.updateMovieByID(movie.getMovieId(), movie);
        assertEquals(updateData, movie.getContent());
	}
	
	@Test
	public void testInsert() throws Exception {
		Movie newMovie = new Movie();
        newMovie.setMovieId("M123");
        newMovie.setActor("new actor");
        newMovie.setContent("new content");
        newMovie.setDirector("new director");
        newMovie.setMovieNameEng("eng");
        newMovie.setMovieNameVn("vn");
        boolean status = movieDAO.insertMovie(newMovie);
        assertTrue(status);
	}
	
	@Test
	public void testDelete() throws Exception {
		boolean status = movieDAO.deleteMovieByID("M123");
		assertTrue(status);
	}
}
