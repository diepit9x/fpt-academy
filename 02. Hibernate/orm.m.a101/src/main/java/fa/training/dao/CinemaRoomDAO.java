package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.CinemaRoom;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface CinemaRoomDAO {
	CinemaRoom getCinemaRoomByID(int cinemaRoomId) throws HibernateException;
	List<CinemaRoom> getAllCinemaRooms() throws HibernateException;
	boolean updateCinemaRoomByID(int cinemaRoomId, CinemaRoom cinemaRoom) throws HibernateException, DataNotFoundException;
	boolean deleteCinemaRoomByID(int cinemaRoomId) throws HibernateException, DataNotFoundException;
	boolean insertCinemaRoom(CinemaRoom cinemaRoom) throws HibernateException, DataAlreadyExistException;
}
