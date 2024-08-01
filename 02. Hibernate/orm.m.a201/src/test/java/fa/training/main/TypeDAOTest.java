package fa.training.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.dao.TypeDAO;
import fa.training.dao.TypeDAOImpl;
import fa.training.entities.Type;

public class TypeDAOTest {
	private static TypeDAO typeDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		typeDAO = new TypeDAOImpl();
	}
	
	@Test
	public void testGetById() {
		Type type = typeDAO.getTypeByID(1);
		assertNotNull(type);
		assertEquals(1, type.getTypeId());
	}
	
	@Test
	public void testGetAll() throws Exception {
		List<Type> types = typeDAO.getAllTypes();
		assertNotNull(types);
		assertFalse(types.isEmpty());
	}
	
	@Test
	public void testUpdate() throws Exception {
		Type type = typeDAO.getTypeByID(1);
		assertNotNull(type);
		String updateData = "update "+LocalDateTime.now();
		type.setTypeDescription(updateData);
        typeDAO.updateTypeByID(type.getTypeId(), type);
        
        type = typeDAO.getTypeByID(type.getTypeId());
        assertEquals(updateData, type.getTypeDescription());
	}
	
	@Test
	public void testInsert() throws Exception {
		Type newType = new Type();
		newType.setTypeDescription("new descr");
		newType.setTypeName("new name 2");
        boolean status = typeDAO.insertType(newType);
        assertTrue(status);
	}
	
	@Test
	public void testDelete() throws Exception {
		boolean status = typeDAO.deleteTypeByID(3);
		assertTrue(status);
	}
}
