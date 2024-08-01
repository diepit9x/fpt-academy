package fa.training.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Date;

import fa.training.dao.CandidateDAO;
import fa.training.dao.CandidateDAOImpl;
import fa.training.entities.Candidate;
import fa.training.entities.Certificate;
import fa.training.entities.Experience;
import fa.training.entities.Fresher;
import fa.training.entities.Intern;

public class CandidateService {

public static void main(String[] args) {
    CandidateDAO candidateDAO = new CandidateDAOImpl();
    
   Candidate c1 = new Experience(1, "ex1", Date.valueOf("2003-10-14"), "0987654321", "email@123", 2, "skill");
   Candidate c2 = new Fresher(2, "f2", Date.valueOf("2003-10-14"), "0987654321", "email@123", Date.valueOf("2003-10-14"), 2, "skill");
   Candidate c3 = new Experience(1, "exxxxxxxxx", Date.valueOf("2003-10-20"), "0987654321111", "email@123222", 2, "skill22222");
   Candidate c4= new Experience(1, "ex1", Date.valueOf("2003-10-14"), "0987654321", "email@123", 2, "skill");
   Candidate c5 = new Intern(15, "xxxxxxxxx", Date.valueOf("2000-1-1"), "099999up2", "email@123up2", "itup2", 1, "bkdnup2");
 
   List<Certificate> certificates = new ArrayList<>();
   certificates.add(new Certificate(1,"c1",1, Date.valueOf("2020-1-1")));
   certificates.add(new Certificate(2,"c2",1, Date.valueOf("2021-1-1")));
   certificates.add(new Certificate(3,"c3",1, Date.valueOf("2021-1-1")));
   
   c5.setCertificates(certificates);
   
   
  // System.out.println("candidate: " + c1.getCandidateCount());
   
   
      try {
////    candidateDAO.updateCandidate(c5);
//	  List<Candidate> candidates = candidateDAO.getAllCandidate();
//	  for (Candidate candidate : candidates) {
//	    candidate.showInfo();;
//	}

	  candidateDAO.addCandidate(c5);
	  
} catch (SQLException e) {
    e.printStackTrace();
} catch (Exception e) {
    e.printStackTrace();
}
    
}

}