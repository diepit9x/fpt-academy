package fa.training.entities;

import java.io.Serializable;
import java.util.Date;

public class FresherCandidate extends Candidate implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date graduationDate;
    private String graduationRank;
    private String education;
    
    public FresherCandidate() {
    }
    
    public FresherCandidate(String firstName, String lastName, Date birthDate, String address, String phone,
	    String email, Date graduationDate, String graduationRank, String education) {
	super(firstName, lastName, birthDate, address, phone, email);
	this.graduationDate = graduationDate;
	this.graduationRank = graduationRank;
	this.education = education;
    }



    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(String graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
	return "FresherCandidate [" + super.toString() + ", graduationDate=" + dateToString(graduationDate) + ", graduationRank=" + graduationRank
		+ ", education=" + education + "]";
    }
    
}
