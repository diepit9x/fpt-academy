package fa.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Type;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;
import fa.training.util.HibernateUtil;

public class TypeDAOImpl implements TypeDAO {

	@Override
	public Type getTypeByID(int typeId) throws HibernateException {
		if (typeId <= 0) {
			return null;
		}
	    Type type = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			type = session.get(Type.class, typeId);
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return type;
	}

	@Override
	public List<Type> getAllTypes() throws HibernateException {
		List<Type> types = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Type> query = session.createQuery("FROM Type", Type.class);
			types = query.list();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return types;
	}

	@Override
	public boolean updateTypeByID(int typeId, Type type) throws HibernateException, DataNotFoundException {
		if (typeId <= 0 || type == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Type existingType = getTypeByID(typeId);
			if (existingType == null) {
				throw new DataNotFoundException();
			}
			//update
			existingType.setTypeName(type.getTypeName());
			existingType.setTypeDescription(type.getTypeDescription());
			session.merge(existingType);
			session.flush();
			status = true;
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean deleteTypeByID(int typeId) throws HibernateException, DataNotFoundException {
		if (typeId <= 0) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Type existingType = getTypeByID(typeId);
			if (existingType == null) {
				throw new DataNotFoundException();
			}
			session.remove(existingType);
			status = true;
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean insertType(Type type) throws HibernateException, DataAlreadyExistException {
		if (type == null) {
			return false;
		}
		boolean status = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			if (type.getTypeId() <= 0) {
				Type existingType = getTypeByID(type.getTypeId());
				if (existingType != null) {
					throw new DataAlreadyExistException();
				}
			}
			session.merge(type);	
			status = true;
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

}
