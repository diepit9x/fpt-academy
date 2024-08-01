package fa.training.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.dao.BillDAO;
import fa.training.dao.BillDAOImpl;
import fa.training.dao.PaymentDAO;
import fa.training.dao.PaymentDAOImpl;
import fa.training.entities.Bill;
import fa.training.entities.Payment;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public class TestPaymentDAO {
	private static PaymentDAO paymentDAO;
	
	@BeforeClass
	public static void setUp() {
		paymentDAO = new PaymentDAOImpl();
	}
	
	@Test
	public void getAll() {
		List<Payment> payments = paymentDAO.getAllPayments();
		assertFalse(payments.isEmpty());
		payments.forEach(p -> System.out.println(p.toString()));
	}
	
	@Test
	public void getById() {
		int paymentReceiptnum = 1;
		Payment payment = paymentDAO.getPaymentByID(paymentReceiptnum);
		assertNotNull(payment);
	}
	
	@Test
	public void update() throws HibernateException, DataNotFoundException {
		int paymentReceiptnum = 1;
		Payment payment = paymentDAO.getPaymentByID(paymentReceiptnum);
		assertNotNull(payment);
		
		payment.setPayDate(LocalDate.now());
		
		boolean status =  paymentDAO.updatePaymentByID(paymentReceiptnum, payment);
		assertTrue(status);
		
		assertEquals(LocalDate.now(), payment.getPayDate());
	}
	
	@Test
	public void insert() throws HibernateException, DataAlreadyExistException {
		BillDAO billDAO = new BillDAOImpl();
		Bill bill = billDAO.getBillByID(7);
		
		Payment payment = new Payment();
		payment.setBill(bill);
		payment.setPayAmount(5);
		
		boolean status = paymentDAO.insertPayment(payment);
		assertTrue(status);
		
	}
	
	@Test
	public void delete() throws HibernateException, DataNotFoundException {
		int paymentReceiptnum = 5;
		boolean status = paymentDAO.deletePaymentByID(paymentReceiptnum);
		assertTrue(status);
	}
	
	@Test
	public void findPaymentByBill() {
		BillDAO billDAO = new BillDAOImpl();
		Bill bill = billDAO.getBillByID(4);
		
		List<Payment> payments = paymentDAO.findPaymentByBill(bill);
		assertFalse(payments.isEmpty());
		payments.forEach(p -> System.out.println(p.toString()));
	}
}
