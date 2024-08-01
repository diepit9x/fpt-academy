package fa.training.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Customer;
import fa.training.exception.DataAlreadyExistException;
import fa.training.util.HibernateUtil;

public class CustomerDAO {
	public List<Customer> getAllCustomers() throws HibernateException {
		List<Customer> customers = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
			customers = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return customers;
	}
	
	public Customer getCustomerById(Integer id) throws HibernateException {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Customer.class, id);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public Customer insertCustomer(Customer customer) throws HibernateException, DataAlreadyExistException  {
		Customer newCustomer = null;
		if (customer == null) {
			return newCustomer;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Customer existingCustomer = getCustomerById(customer.getId());
			if (existingCustomer != null) {
				throw new DataAlreadyExistException();
			}
			Transaction transaction = session.beginTransaction();
			newCustomer =  session.merge(customer);
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return newCustomer;
	}
}
