package fa.training.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Seat;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;

public class SeatDAOImpl implements SeatDAO {

	@Override
	public Seat getSeatByID(int seatId) throws HibernateException {
		if (seatId <= 0) {
			return null;
		}
		Seat seat = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Query<Seat>query = session.createNativeQuery("SELECT * FROM MovieTheater.SEAT WHERE SEAT_ID = :seatId", Seat.class);
			query.setParameter("seatId", seatId);
			if (!query.list().isEmpty()) {
				seat = query.getSingleResult();
			}

			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return seat;
	}

	@Override
	public List<Seat> getAllSeats() throws HibernateException {
		List<Seat> seats  = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Query<Seat>query = session.createNativeQuery("SELECT * FROM MovieTheater.SEAT", Seat.class);
			seats = query.list();
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return seats;
	}

	@Override
	public boolean updateSeatByID(int seatId, Seat seat) throws HibernateException, DataNotFoundException {
		if (seatId <= 0 || seat == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Seat existingSeat = getSeatByID(seatId);
			if (existingSeat == null) {
				throw new DataNotFoundException();
			}
			//update data
			existingSeat.setCinemaRoom(seat.getCinemaRoom());
			existingSeat.setSeatColumn(seat.getSeatColumn());
			existingSeat.setSeatRow(seat.getSeatRow());
			existingSeat.setSeatStatus(seat.getSeatStatus());
			existingSeat.setSeatType(seat.getSeatType());
			//save to database
			session.merge(existingSeat);
			status = true;
			
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean deleteSeatByID(int seatId) throws HibernateException, DataNotFoundException {
		if (seatId <= 0) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Seat existingSeat = getSeatByID(seatId);
			if (existingSeat == null) {
				throw new DataNotFoundException();
			}
			//save to database
			session.remove(existingSeat);
			status = true;
			
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean insertSeat(Seat seat) throws HibernateException, DataAlreadyExistException {
		if (seat == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			if (seat.getSeatId() > 0) {
				Seat existingSeat = getSeatByID(seat.getSeatId());
				if (existingSeat != null) {
					throw new DataAlreadyExistException();
				}
			}
			//save to database
			session.merge(seat);
			status = true;
			
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

}
