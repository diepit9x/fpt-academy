package fa.training.main;

import java.util.List;

import org.hibernate.HibernateException;

import fa.training.dao.EntryTestDAO;
import fa.training.dao.EntryTestDAOImpl;
import fa.training.entities.Candidate;
import fa.training.entities.EntryTest;
import fa.training.exception.DataAlreadyExistException;
import fa.training.util.HibernateValidator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws HibernateException, DataAlreadyExistException
    {

//    	Candidate candidate = new Candidate();
//    	EntryTest entryTest = new EntryTest();
//    	entryTest.setResult("12312");
//    	entryTest.setTechnicalResult(8.5F);
//    	
//    	List<String> errorList = HibernateValidator.getViolations(entryTest);
//    	if (!errorList.isEmpty()) {
//			errorList.forEach(System.out::println);
//		} else {
//			System.out.println("Valid");
//		}

//    	EntryTestDAO entryTestDAO = new EntryTestDAOImpl();
//    	EntryTest entryTest = new EntryTest();
//    	entryTest.setEntryTestSkill("skill");
//    	entryTest.setResult("pass");
////    	entryTestDAO.insertEntryTest(entryTest);
//    	
//    	for (EntryTest e : entryTestDAO.getAllEntryTests()) {
//			System.out.println(e.toString());
//		}
    	
    	
    	
    }
}
