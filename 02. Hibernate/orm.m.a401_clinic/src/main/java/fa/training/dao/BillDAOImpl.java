package fa.training.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Bill;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;
import fa.training.util.LoggerUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class BillDAOImpl implements BillDAO {
	private static final Logger logger = LoggerUtil.getLogger();

	@Override
	public Bill getBillByID(int billId) throws HibernateException {
		if (billId <= 0) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Bill.class, billId);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Bill> getAllBills() throws HibernateException {
		List<Bill> bills = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Bill> query = session.createQuery("FROM Bill", Bill.class);
			bills = query.getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return bills;
	}

	@Override
	public boolean updateBillByID(int billId, Bill bill) throws HibernateException, DataNotFoundException {
		if ( billId <= 0 || bill == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Bill existingBill = getBillByID(billId);
			if (existingBill == null) {
				throw new DataNotFoundException();
			}
			bill.setBillNumber(existingBill.getBillNumber());
			Transaction transaction = session.beginTransaction();
			session.merge(bill);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteBillByID(int billId) throws HibernateException, DataNotFoundException {
		if ( billId <= 0) {
			return false;
		}
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Bill existingBill = getBillByID(billId);
			if (existingBill == null) {
				throw new DataNotFoundException();
			}
			transaction = session.beginTransaction();
			session.remove(existingBill);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean insertBill(Bill bill) throws HibernateException, DataAlreadyExistException {
		if ( bill == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Bill existingBill = getBillByID(bill.getBillNumber());
			if (existingBill != null) {
				throw new DataAlreadyExistException();
			}
			Transaction transaction = session.beginTransaction();
			session.merge(bill);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Bill> findByBillDate(LocalDate billDate) throws HibernateException {
		List<Bill> bills = new ArrayList<>();
		if (billDate == null) {
			return bills;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Bill> query = session.createQuery("FROM Bill b WHERE b.billDate = :billDate", Bill.class);
			query.setParameter("billDate", billDate);
			bills = query.getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return bills;
	}

	@Override
	public List<Bill> pagingBillByCriteria(int pageNumber, int pageSize) throws HibernateException {
		pageNumber = pageNumber <= 0 ? 1 : pageNumber;
		pageSize = pageNumber <= 0 ? 1 : pageSize;
		List<Bill> bills = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Bill> criteriaQuery = criteriaBuilder.createQuery(Bill.class);
			Root<Bill> root = criteriaQuery.from(Bill.class);
			criteriaQuery.select(root);
			Query<Bill> query = session.createQuery(criteriaQuery);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			bills = query.getResultList();
			
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return bills;
	}

}
