package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Bill;
import fa.training.entities.Payment;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface PaymentDAO {
	List<Payment> findPaymentByBill(Bill bill) throws HibernateException;
	
	Payment getPaymentByID(int payReceptnum) throws HibernateException;
	List<Payment> getAllPayments() throws HibernateException;
	boolean updatePaymentByID(int payReceptnum, Payment payment) throws HibernateException, DataNotFoundException;
	boolean deletePaymentByID(int payReceptnum) throws HibernateException, DataNotFoundException;
	boolean insertPayment(Payment payment) throws HibernateException, DataAlreadyExistException;
	
}
