package fa.training.dao;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.entities.Type;
import fa.training.exception.DataAlreadyExistException;
import fa.training.exception.DataNotFoundException;

public interface TypeDAO {
	Type getTypeByID(int typeId) throws HibernateException;
	List<Type> getAllTypes() throws HibernateException;
	boolean updateTypeByID(int typeId, Type type) throws HibernateException, DataNotFoundException;
	boolean deleteTypeByID(int typeId) throws HibernateException, DataNotFoundException;
	boolean insertType(Type type) throws HibernateException, DataAlreadyExistException;
}
