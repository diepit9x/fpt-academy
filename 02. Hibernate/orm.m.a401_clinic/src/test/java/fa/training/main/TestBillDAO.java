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

import fa.training.dao.AppointmentDAO;
import fa.training.dao.AppointmentDAOImpl;
import fa.training.dao.BillDAO;
import fa.training.dao.BillDAOImpl;
import fa.training.entities.Appointment;
import fa.training.entities.Bill;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public class TestBillDAO {
	private static BillDAO billDAO;
	
	@BeforeClass
	public static void setUp() {
		billDAO = new BillDAOImpl();
	}
	
	@Test
	public void getAll() {
		List<Bill> bills = billDAO.getAllBills();
		assertFalse(bills.isEmpty());
		bills.forEach(b -> System.out.println(b.toString()));
	}
	
	@Test
	public void getById() {
		int billNumber = 4;
		Bill bill = billDAO.getBillByID(billNumber);
		assertNotNull(bill);
	}
	
	@Test
	public void update() throws HibernateException, DataNotFoundException {
		int billNumber = 1;
		Bill bill = billDAO.getBillByID(billNumber);
		assertNotNull(bill);
		
		LocalDate getDate = LocalDate.now();
		bill.setBillDate(getDate);
		
		boolean status = billDAO.updateBillByID(billNumber, bill);
		assertTrue(status);
		
		assertEquals(getDate, bill.getBillDate());
		
	}
	
	@Test
	public void insert() throws HibernateException, DataAlreadyExistException {
		AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		Appointment appointment  = appointmentDAO.getAppointmentByID(LocalDate.of(2022, 11, 2), 1);
		
		Bill newBill = new Bill();
		newBill.setAppointment(appointment);
		newBill.setBillDate(LocalDate.now());
		
		boolean status = billDAO.insertBill(newBill);
		assertTrue(status);
	}
	
	@Test
	public void delete() throws HibernateException, DataNotFoundException {
		int billNumber = 6;
		boolean status = billDAO.deleteBillByID(billNumber);
		assertTrue(status);
	}
	
	@Test
	public void findByBillDate() {
		LocalDate billDate = LocalDate.of(2024, 7, 7);
		List<Bill> bills = billDAO.findByBillDate(billDate);
		assertFalse(bills.isEmpty());
		bills.forEach(b -> System.out.println(b.toString()));
	}
	
	@Test
	public void pagingBillByCriteria() {
		int pageNumber = 1;
		int pageSize = 1;
		
		List<Bill> bills = billDAO.pagingBillByCriteria(pageNumber, pageSize);
		assertFalse(bills.isEmpty());
		
		bills.forEach(b -> System.out.println(b.toString()));
	}
}
