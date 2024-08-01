package fa.training.util;

public class SQLCommand {
    //Candidate
    public static final String CANDIDATE_QUERY = "SELECT candidateId, fullName, birthDay, phone, email, candidateType FROM Candidate";
    public static final String CANDIDATE_QUERY_UPDATE = "SELECT fullName, birthDay, phone, email, candidateType FROM Candidate WHERE candidateId = ?";
    public static final String CANDIDATE_QUERY_GET_ALL = "SELECT c.candidateId, c.fullName, c.birthDay, c.phone, c.email, c.candidateType, "
	    					+"e.expInYear, e.proSkill, "
	    					+"f.graduationDate, f.graduationRank, f.education, "
	    					+"i.major, i.semester, i.universityName"
	    					+" FROM Candidate c "
	    					+"LEFT JOIN Experience e ON c.candidateId = e.candidateId "
	    					+"LEFT JOIN Fresher f ON c.candidateId = f.candidateId "
	    					+"LEFT JOIN Intern i ON c.candidateId = i.candidateId";
  
    //Experience
    public static final String EXPERIENCE_QUERY = 	"SELECT candidateId, expInYear, proSkill FROM Experience";
    public static final String EXPERIENCE_QUERY_UPDATE = "SELECT expInYear, proSkill FROM Experience WHERE candidateId = ?";
    
    //Fresher
    public static final String FRESHER_QUERY = "SELECT candidateId, graduationDate, graduationRank, education FROM Fresher";
    public static final String FRESHER_QUERY_UPDATE = "SELECT graduationDate, graduationRank, education FROM Fresher  WHERE candidateId = ?";
    
    //Intern
    public static final String INTERN_QUERY = "SELECT candidateId, major, semester, universityName FROM Intern";
    public static final String INTERN_QUERY_UPDATE = "SELECT candidateId, major, semester, universityName FROM Intern  WHERE candidateId = ?";
    
    //Certificate
    public static final String CERTIFICATE_QUERY = "SELECT certificateId, candidateId, certificateName,certificateRank, certificateDate  FROM Certificate";
    public static final String CERTIFICATE_QUERY_BY_CANDIDATEID = "SELECT *  FROM Certificate WHERE candidateId = ?";
}
//