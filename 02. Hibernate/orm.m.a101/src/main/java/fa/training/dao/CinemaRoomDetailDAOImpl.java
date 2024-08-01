package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.CinemaRoomDetail;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;

public class CinemaRoomDetailDAOImpl implements CinemaRoomDetailDAO {

	@Override
	public CinemaRoomDetail getCinemaRoomDetailByID(int cinemaRoomDetailId) throws HibernateException {
		if (cinemaRoomDetailId <= 0) {
			return null;
		}
		CinemaRoomDetail cinemaRoomDetail = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			Query<CinemaRoomDetail>query = session.createNativeQuery("SELECT * FROM MovieTheater.CINEMA_ROOM_DETAIL WHERE CINEMA_ROOM_DETAIL_ID = :cinemaRoomDetailId", CinemaRoomDetail.class);
			query.setParameter("cinemaRoomDetailId", cinemaRoomDetailId);
			cinemaRoomDetail = query.getSingleResult();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cinemaRoomDetail;
	}

	@Override
	public List<CinemaRoomDetail> getAllCinemaRoomDetails() throws HibernateException {
		List<CinemaRoomDetail> cinemaRoomDetails  = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			Query<CinemaRoomDetail>query = session.createNativeQuery("SELECT * FROM MovieTheater.CINEMA_ROOM_DETAIL", CinemaRoomDetail.class);
			cinemaRoomDetails = query.list();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cinemaRoomDetails;
	}

	@Override
	public boolean updateCinemaRoomDetailByID(int cinemaRoomDetailId, CinemaRoomDetail cinemaRoomDetail)
			throws HibernateException, DataNotFoundException {
		if (cinemaRoomDetailId <= 0 || cinemaRoomDetail == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			CinemaRoomDetail existingCinemaRoomDetail = getCinemaRoomDetailByID(cinemaRoomDetailId);
			if (existingCinemaRoomDetail == null) {
				throw new DataNotFoundException();
			}
			//update data
			existingCinemaRoomDetail.setCinemaRoom(cinemaRoomDetail.getCinemaRoom());
			existingCinemaRoomDetail.setRoomRate(cinemaRoomDetail.getRoomRate());
			existingCinemaRoomDetail.setActiveDate(cinemaRoomDetail.getActiveDate());
			existingCinemaRoomDetail.setRoomDescription(cinemaRoomDetail.getRoomDescription());
			//save to database
			session.merge(existingCinemaRoomDetail);
			status = true;
			
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean deleteCinemaRoomDetailByID(int cinemaRoomDetailId) throws HibernateException, DataNotFoundException {
		if (cinemaRoomDetailId <= 0) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			CinemaRoomDetail existingCinemaRoomDetail = getCinemaRoomDetailByID(cinemaRoomDetailId);
			if (existingCinemaRoomDetail == null) {
				throw new DataNotFoundException();
			}
			session.remove(existingCinemaRoomDetail);
			status = true;
			
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean insertCinemaRoomDetail(CinemaRoomDetail cinemaRoomDetail)
			throws HibernateException, DataAlreadyExistException {
		if (cinemaRoomDetail == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			if (cinemaRoomDetail.getCinemaRoomDetailId() > 0) {
				CinemaRoomDetail existingCinemaRoomDetail = getCinemaRoomDetailByID(cinemaRoomDetail.getCinemaRoomDetailId());
				if (existingCinemaRoomDetail != null) {
					throw new DataAlreadyExistException();
				}
			}
			session.merge(cinemaRoomDetail);
			status = true;
			
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

}
