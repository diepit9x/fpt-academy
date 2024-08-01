package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.CinemaRoom;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;

public class CinemaRoomDAOImpl implements CinemaRoomDAO {

	@Override
	public CinemaRoom getCinemaRoomByID(int cinemaRoomId) throws HibernateException {
		if (cinemaRoomId <= 0) {
			return null;
		}
		CinemaRoom cinemaRoom = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<CinemaRoom>query = session.createNativeQuery("SELECT * FROM MovieTheater.CINEMA_ROOM WHERE CINEMA_ROOM_ID = :cinemaRoomId", CinemaRoom.class);
			query.setParameter("cinemaRoomId", cinemaRoomId);
			cinemaRoom = query.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cinemaRoom;
	}

	@Override
	public List<CinemaRoom> getAllCinemaRooms() throws HibernateException {
		List<CinemaRoom> cinemaRooms  = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<CinemaRoom> query = session.createQuery("FROM CinemaRoom c", CinemaRoom.class);
			cinemaRooms = query.list();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cinemaRooms;
	}

	@Override
	public boolean updateCinemaRoomByID(int cinemaRoomId, CinemaRoom cinemaRoom) throws HibernateException {
		if (cinemaRoomId <= 0 || cinemaRoom == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			CinemaRoom existingCinemaRoom = getCinemaRoomByID(cinemaRoomId);
			if (existingCinemaRoom == null) {
				throw new DataNotFoundException();
			}
			//update data
			existingCinemaRoom.setCinemaRoomName(cinemaRoom.getCinemaRoomName());
			existingCinemaRoom.setSeatQuantity(cinemaRoom.getSeatQuantity());
			//save to database
			session.merge(existingCinemaRoom);
			status = true;
			
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean deleteCinemaRoomByID(int cinemaRoomId) throws HibernateException, DataNotFoundException {
		if (cinemaRoomId <= 0) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			CinemaRoom existingCinemaRoom = getCinemaRoomByID(cinemaRoomId);
			if (existingCinemaRoom == null) {
				throw new DataNotFoundException();
			}
			//update data
			session.remove(existingCinemaRoom);
			status = true;
			
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean insertCinemaRoom(CinemaRoom cinemaRoom) throws HibernateException {
		if (cinemaRoom == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			if (cinemaRoom.getCinemaRoomId() != 0) {
				CinemaRoom existingCinemaRoom = getCinemaRoomByID(cinemaRoom.getCinemaRoomId());
				if (existingCinemaRoom != null) {
					throw new DataAlreadyExistException();
				}
			}
			//save to database
			session.merge(cinemaRoom);
			status = true;
			
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

}
