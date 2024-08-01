package daos;

import org.hibernate.HibernateException;

import entities.Member;

public interface MemberDAO {
	Member findById(Integer id) throws HibernateException;
	Member findByUsername(String username) throws HibernateException;
	Member findByEmail(String email) throws HibernateException;
	Member login(String username, String password) throws HibernateException;
	Boolean register(Member member) throws HibernateException;
	Boolean update(Member member) throws HibernateException;
}
