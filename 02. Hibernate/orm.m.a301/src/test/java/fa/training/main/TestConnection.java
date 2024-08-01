package fa.training.main;

import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.junit.Test;

import fa.training.util.HibernateUtil;

public class TestConnection {
	@Test
	public void testConnectin() throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean status = session != null;
		assertTrue(status);
	}
}
