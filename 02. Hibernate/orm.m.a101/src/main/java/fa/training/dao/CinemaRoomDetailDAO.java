package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.CinemaRoomDetail;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface CinemaRoomDetailDAO {
	CinemaRoomDetail getCinemaRoomDetailByID(int cinemaRoomDetailId) throws HibernateException;
	List<CinemaRoomDetail> getAllCinemaRoomDetails() throws HibernateException;
	boolean updateCinemaRoomDetailByID(int cinemaRoomDetailId, CinemaRoomDetail cinemaRoomDetail) throws HibernateException, DataNotFoundException;
	boolean deleteCinemaRoomDetailByID(int cinemaRoomDetailId) throws HibernateException, DataNotFoundException;
	boolean insertCinemaRoomDetail(CinemaRoomDetail cinemaRoomDetail) throws HibernateException, DataAlreadyExistException;
}
