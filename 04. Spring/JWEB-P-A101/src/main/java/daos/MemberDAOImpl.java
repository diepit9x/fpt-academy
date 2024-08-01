package daos;


import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.Member;
import util.HibernateUtil;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public Member login(String email, String password) throws HibernateException {
		if (email == null || password == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Member> query = session.createQuery("FROM Member m WHERE m.email = :email AND m.password = :password", Member.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			return query.getSingleResultOrNull();
		} catch (HibernateError e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean register(Member member) throws HibernateException {
		if (member == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			session.persist(member);
			t.commit();
			return true;
		} catch (HibernateError e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean update(Member member) throws HibernateException {
		if (member == null) {
			return false;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			session.merge(member);
			t.commit();
		} catch (HibernateError e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Member findById(Integer id) throws HibernateException {
		if (id == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Member.class, id);
		} catch (HibernateError e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Member findByUsername(String username) throws HibernateException {
		if (username == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Member> query = session.createQuery("FROM Member m WHERE m.username = :username", Member.class);
			query.setParameter("username", username);
			return query.getSingleResultOrNull();
		} catch (HibernateError e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Member findByEmail(String email) throws HibernateException {
		if (email == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Member> query = session.createQuery("FROM Member m WHERE m.email = :email", Member.class);
			query.setParameter("email", email);
			return query.getSingleResultOrNull();
		} catch (HibernateError e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
