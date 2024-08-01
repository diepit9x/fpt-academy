package fa.training.config;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.entities.Lession;
import fa.training.entities.Student;
import fa.training.util.HibernateUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Student student = new Student("oneoen234234");
//			Student student1 = new Student("xxxxxyyyy");
			
//			session.merge(student);
//			session.merge(student1);
			//Query<Student> query = session.createNativeQuery("SELECT * FROM dbo.Students WHERE name != :name", Student.class);
			//query.setParameter("name", "");
			
			
//			List<Student> students = query.list();
//			for (Student student : students) {
//				System.out.println(student.toString());
//			}
			
			Lession lession = new Lession();
			lession.setId(2);
			lession.setName("lession name");
			
			student.setLession(lession);
			
			session.merge(student);

			
			transaction.commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
