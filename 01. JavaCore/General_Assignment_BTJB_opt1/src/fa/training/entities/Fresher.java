package fa.training.entities;

import java.sql.Date;

import fa.training.exception.BirthDayException;
import fa.training.util.Constant;
import fa.training.util.Validator;

public class Fresher extends Candidate {
    private Date graduationDate;
    private int graduationRank;
    private String education;
    
    public Fresher() {
	super();
    }

    public Fresher(int candidateId, String fullName, Date birthDay, String phone, String email,
	    Date graduationDate, int graduationRank, String education) {
	super(candidateId, fullName, birthDay, phone, email, 1);
	this.graduationDate = graduationDate;
	this.graduationRank = graduationRank;
	this.education = education;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) throws BirthDayException {
	if (!Validator.isDate(graduationDate)) {
	    throw new BirthDayException(Constant.DATE_EXCEPTION_MESSAGE);
	}
	this.graduationDate = Date.valueOf(graduationDate);
    }
    
    public void setGraduationDate(Date graduationDate) {
	this.graduationDate = graduationDate;
    }

    public int getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(int graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public void showMe() {
        System.out.println("Fresher Candidate: " + getFullName());
        System.out.println("Graduation Date: " + graduationDate);
        System.out.println("Graduation Rank: " + graduationRank);
        System.out.println("Education: " + education);
    }

    @Override
    public String toString() {
	return "Fresher [" + super.toString() + ", graduationDate=" + graduationDate + ", graduationRank=" + graduationRank + ", education="
		+ education + "]";
    }
    

}
