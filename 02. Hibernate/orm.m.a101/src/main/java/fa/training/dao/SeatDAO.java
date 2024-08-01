package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Seat;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface SeatDAO {
	Seat getSeatByID(int seatId) throws HibernateException;
	List<Seat> getAllSeats() throws HibernateException;
	boolean updateSeatByID(int seatId, Seat seat) throws HibernateException, DataNotFoundException;
	boolean deleteSeatByID(int seatId) throws HibernateException, DataNotFoundException;
	boolean insertSeat(Seat seat) throws HibernateException, DataAlreadyExistException;
}
