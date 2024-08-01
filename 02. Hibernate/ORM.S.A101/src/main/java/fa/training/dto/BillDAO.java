package fa.training.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import fa.training.entities.Bill;
import fa.training.exception.BillException;
import fa.training.exception.DataAlreadyExistException;
import fa.training.util.HibernateUtil;
import fa.training.util.HibernateValidator;

public class BillDAO {
	public List<Bill> getAllBills() throws HibernateException {
		List<Bill> bills = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Bill> query = session.createQuery("FROM Bill", Bill.class);
			bills = query.getResultList();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return bills;
	}
	
	public Bill getBillById(Integer id) {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Bill.class, id);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public Bill insertBill(Bill bill) throws HibernateException, DataAlreadyExistException, BillException {
		Bill newBill = null;
		if (bill == null) {
			return newBill;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Bill existingBill = getBillById(bill.getId());
			if (existingBill != null) {
				throw new DataAlreadyExistException();
			}

			List<String> errorMessages = HibernateValidator.getViolations(bill);
			if (errorMessages.isEmpty()) {
//				check apartment
				Query<Long> queryAp= session.createQuery("SELECT COUNT(b) FROM Bill b WHERE b.apartment = :apartment", Long.class);
				queryAp.setParameter("apartment", bill.getApartment());
				Long countAp = queryAp.uniqueResult();
				if (countAp > 0) {
					throw new BillException("Can ho nay da ban");
				}
//				check employee
				Query<Long> queryEmp = session.createQuery("SELECT COUNT(b) FROM Bill b WHERE b.employee = :employee", Long.class);
				queryEmp.setParameter("employee", bill.getEmployee());
				Long countEmp = queryEmp.uniqueResult();
				if (countEmp >= 3) {
					throw new BillException("Nhan vien da ban qua so can ho cho phep");
				}
//				check customer
				Query<Long> queryCus = session.createQuery("SELECT COUNT(b) FROM Bill b WHERE b.customer = :customer", Long.class);
				queryCus.setParameter("customer", bill.getCustomer());
				Long countCus = queryCus.uniqueResult();
				if (countCus >= 2) {
					throw new BillException("Da het so luong can ho uu dai cho khach hang");
				}
				
				Transaction transaction = session.beginTransaction();
				
				MutationQuery query = session.createMutationQuery( "UPDATE Apartment ap SET ap.status = 'da ban' WHERE ap.id = :id");
				query.setParameter("id", bill.getApartment().getId());
				query.executeUpdate();
				
				newBill =  session.merge(bill);
				
				transaction.commit();
			} else {
				errorMessages.forEach(System.err::println);
			}
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return newBill;
	}
}
