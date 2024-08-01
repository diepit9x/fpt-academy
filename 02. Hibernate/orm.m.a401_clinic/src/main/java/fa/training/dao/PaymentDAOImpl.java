package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Bill;
import fa.training.entities.Payment;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;
import fa.training.util.LoggerUtil;

public class PaymentDAOImpl implements PaymentDAO {
	private static final Logger logger = LoggerUtil.getLogger();

	@Override
	public Payment getPaymentByID(int payReceptnum) throws HibernateException {
		if (payReceptnum <= 0) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Payment.class, payReceptnum);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Payment> getAllPayments() throws HibernateException {
		List<Payment> payments = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Payment> query = session.createQuery("FROM Payment", Payment.class);
			payments = query.getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return payments;
	}

	@Override
	public boolean updatePaymentByID(int payReceptnum, Payment payment) throws HibernateException, DataNotFoundException {
		if (payReceptnum <= 0 || payment == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Payment existingPayment = getPaymentByID(payReceptnum);
			if (existingPayment == null) {
				throw new DataNotFoundException();
			}
			payment.setPayReceptnum(existingPayment.getPayReceptnum());
			Transaction transaction = session.beginTransaction();
			session.merge(payment);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deletePaymentByID(int payReceptnum) throws HibernateException, DataNotFoundException {
		if (payReceptnum <= 0) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Payment existingPayment = getPaymentByID(payReceptnum);
			if (existingPayment == null) {
				throw new DataNotFoundException();
			}
			Transaction transaction = session.beginTransaction();
			session.remove(existingPayment);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean insertPayment(Payment payment) throws HibernateException, DataAlreadyExistException {
		if (payment == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Payment existingPayment = getPaymentByID(payment.getPayReceptnum());
			if (existingPayment != null) {
				throw new DataAlreadyExistException();
			}
			Transaction transaction = session.beginTransaction();
			session.merge(payment);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Payment> findPaymentByBill(Bill bill) throws HibernateException {
		List<Payment> payments = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Payment> query = session.createQuery("FROM Payment p WHERE p.bill = :bill", Payment.class);
			query.setParameter("bill", bill);
			payments = query.getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return payments;
	}


}
