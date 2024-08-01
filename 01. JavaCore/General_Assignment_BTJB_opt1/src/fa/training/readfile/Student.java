package fa.training.readfile;

import fa.training.exception.EmailException;
import fa.training.util.PhoneException;

public class Student {
    private String stdNo;
    private String stdName;
    private String phone;
    private String email;
    private int gradePoint;
    
    public Student() {
	// TODO Auto-generated constructor stub
    }
    
    
    public Student(String stdNo, String stdName, String phone, String email, int gradePoint) {
	super();
	this.stdNo = stdNo;
	this.stdName = stdName;
	this.phone = phone;
	this.email = email;
	this.gradePoint = gradePoint;
    }


    public String getStdNo() {
        return stdNo;
    }

    public void setStdNo(String stdNo) {
        this.stdNo = stdNo;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws PhoneException {
	if (!Validator.isPhone(phone)) {
	    throw new PhoneException();
	}
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailException {
	if (!Validator.isEmail(email)) {
	    throw new EmailException();
	}
        this.email = email;
    }

    public int getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(int gradePoint) {
        this.gradePoint = gradePoint;
    }
    
    public void setGradePoint(String gradePoint) throws NumberFormatException {
        this.gradePoint = Integer.parseInt(gradePoint);
    }


    @Override
    public String toString() {
	return "Student [stdNo=" + stdNo + ", stdName=" + stdName + ", phone=" + phone + ", email=" + email
		+ ", gradePoint=" + gradePoint + "]";
    }
    
}
