package fa.training.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Bill;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface BillDAO {
	List<Bill> findByBillDate(LocalDate billDate) throws HibernateException;
	List<Bill> pagingBillByCriteria(int pageNumber, int pageSize)  throws HibernateException;
	
	Bill getBillByID(int billId) throws HibernateException;
	List<Bill> getAllBills() throws HibernateException;
	boolean updateBillByID(int billId, Bill bill) throws HibernateException, DataNotFoundException;
	boolean deleteBillByID(int billId) throws HibernateException, DataNotFoundException;
	boolean insertBill(Bill bill) throws HibernateException, DataAlreadyExistException;
}
