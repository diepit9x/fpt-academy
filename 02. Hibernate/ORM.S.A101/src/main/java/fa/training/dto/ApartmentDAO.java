package fa.training.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import fa.training.entities.Apartment;
import fa.training.exception.DataAlreadyExistException;
import fa.training.util.HibernateUtil;
import fa.training.util.HibernateValidator;

public class ApartmentDAO {
	public List<Apartment> findByNumBedroom(Integer numberBedroom) throws HibernateException {
		List<Apartment> apartments = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Apartment> query = session.createQuery("FROM Apartment ap WHERE ap.status = 'chua ban' AND ap.numberBedroom = :numberBedroom", Apartment.class);
			query.setParameter("numberBedroom", numberBedroom);
			apartments = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return apartments;
	}
	
	public List<Apartment> getAllApartments() throws HibernateException {
		List<Apartment> apartments = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Apartment> query = session.createQuery("FROM Apartment", Apartment.class);
			apartments = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return apartments;
	}
	
	public Apartment getApartmentById(Integer id) {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Apartment.class, id);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public Apartment insertApartment(Apartment apartment) throws HibernateException, DataAlreadyExistException {
		Apartment newApartment = null;
		if (apartment == null) {
			return newApartment;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Apartment existingApartment = getApartmentById(apartment.getId());
			if (existingApartment != null) {
				throw new DataAlreadyExistException();
			}
			List<String> errorMessages = HibernateValidator.getViolations(apartment);
			if (errorMessages.isEmpty()) {
				Transaction transaction = session.beginTransaction();
				newApartment =  session.merge(apartment);
				transaction.commit();
			} else {
				errorMessages.forEach(System.out::println);
			}
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return newApartment;
	}
	
	public boolean updateStatus() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
	        MutationQuery query = session.createMutationQuery(
	            "UPDATE Apartment ap SET ap.status = 'da ban' WHERE ap.id IN (SELECT b.apartment.id FROM Bill b)"
	        );
	        query.executeUpdate();

	        MutationQuery query2 = session.createMutationQuery(
	            "UPDATE Apartment ap SET ap.status = 'chua ban' WHERE ap.id NOT IN (SELECT b.apartment.id FROM Bill b)"
	        );
	        query2.executeUpdate();
			
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println(e.getMessage());
		}
		return false;
	}
}
