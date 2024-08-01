package fa.training.entities;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import fa.training.exception.BirthDayException;
import fa.training.exception.EmailException;
import fa.training.util.Constant;
import fa.training.util.Validator;

public abstract class Candidate {
    private int candidateId;
    private String fullName;
    private Date birthDay;
    private String phone;
    private String email;
    private int candidateType;
    private static int candidateCount;
    private List<Certificate> certificates;
 
    public Candidate() {
    }

    public Candidate(int candidateId, String fullName, Date birthDay, String phone, String email,
	    int candidateType) {
	super();
	this.candidateId = candidateId;
	this.fullName = fullName;
	this.birthDay = birthDay;
	this.phone = phone;
	this.email = email;
	this.candidateType = candidateType;
	this.certificates = new ArrayList<>();
	candidateCount++;
    }

    public int getCandidateId() {
	return candidateId;
    }

    public void setCandidateId(int candidateId) {
	this.candidateId = candidateId;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public Date getBirthDay() {
	return birthDay;
    }

    public void setBirthDay(String birthDay) throws BirthDayException {
	if (!Validator.isDate(birthDay)) {
	    throw new BirthDayException(Constant.BIRTHDAY_EXCEPTION_MESSAGE);
	}
	this.birthDay = Date.valueOf(birthDay);
    }
    
    public void setBirthDay(Date birthDay) {
	this.birthDay = birthDay;
    }
    
    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) throws EmailException{
	if (!Validator.isEmail(email)) {
	    throw new EmailException(Constant.EMAIL_EXCEPTION_MESSAGE);
	}
	this.email = email;
    }

    public int getCandidateType() {
	return candidateType;
    }

    public void setCandidateType(int candidateType) {
	this.candidateType = candidateType;
    }

    public static int getCandidateCount() {
	return candidateCount;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
    
    public void addCertificate(Certificate certificate) {
        certificates.add(certificate);
    }
    
    public void showInfo() {
	System.out.println(toString());
    }

    @Override
    public String toString() {
	return "candidateId=" + candidateId + ", fullName=" + fullName + ", birthDay=" + birthDay
		+ ", phone=" + phone + ", email=" + email + ", candidateType=" + candidateType + ", certificates="+certificates.toString();
    }

    @Override
    public int hashCode() {
	return Objects.hash(candidateId);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Candidate other = (Candidate) obj;
	return candidateId == other.candidateId;
    }

    public abstract void showMe();

}
