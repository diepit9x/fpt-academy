package fa.training.entities;

import java.sql.Date;

public class Intern extends Candidate {
    private String major;
    private int semester;
    private String universityName;

    public Intern() {
	super();
    }

    public Intern(int candidateId, String fullName, Date birthDay, String phone, String email,
	    String major, int semester, String universityName) {
	super(candidateId, fullName, birthDay, phone, email, 2);
	this.major = major;
	this.semester = semester;
	this.universityName = universityName;
    }


    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public void showMe() {
	System.out.println("Intern Candidate: " + getFullName());
        System.out.println("Majors: " + major);
        System.out.println("Semester: " + semester);
        System.out.println("University Name: " + universityName);
    }

    @Override
    public String toString() {
	return "Intern [" + super.toString() + ", major=" + major + ", semester=" + semester + ", universityName=" + universityName + "]";
    }
}
