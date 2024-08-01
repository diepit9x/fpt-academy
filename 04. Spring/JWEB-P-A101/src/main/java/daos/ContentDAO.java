package daos;

import java.util.List;

import org.hibernate.HibernateException;

import entities.Content;

public interface ContentDAO {
	List<Content> getAll() throws HibernateException;
	Boolean insert(Content content) throws HibernateException;
}
