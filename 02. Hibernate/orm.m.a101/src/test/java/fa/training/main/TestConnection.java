package fa.training.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import fa.training.util.HibernateUtil;

import static org.junit.Assert.assertNotNull;

public class TestConnection {
	 @Test
	    public void testConnection() {
	        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	        assertNotNull("Session factory should not be null", sessionFactory);

	        try (Session session = sessionFactory.openSession()) {
	            assertNotNull("Session should not be null", session);
	            System.out.println("Connection successful!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            assert false : "Failed to establish a connection!";
	        }
	    }
}
