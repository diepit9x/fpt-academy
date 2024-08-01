package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.Content;
import util.HibernateUtil;

public class ContentDAOImpl implements ContentDAO {

	@Override
	public List<Content> getAll() throws HibernateException {
		List<Content> contents = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Content> query = session.createQuery("FROM Content", Content.class);
			contents = query.getResultList();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return contents;
	}

	@Override
	public Boolean insert(Content content) throws HibernateException {
		if (content == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			session.persist(t);
			t.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
