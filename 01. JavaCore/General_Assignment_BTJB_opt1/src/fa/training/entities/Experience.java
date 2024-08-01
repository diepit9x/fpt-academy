package fa.training.entities;

import java.sql.Date;

public class Experience extends Candidate {
    private int expInYear;
    private String proSkill;

    public Experience() {
	super();
    }

    public Experience(int candidateId, String fullName, Date birthDay, String phone, String email, int expInYear, String proSkill) {
	super(candidateId, fullName, birthDay, phone, email, 0);
	this.expInYear = expInYear;
	this.proSkill = proSkill;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    @Override
    public void showMe() {
        System.out.println("Experience Candidate: " + getFullName());
        System.out.println("Years of Experience: " + expInYear);
        System.out.println("Professional Skill: " + proSkill);
    }

    @Override
    public String toString() {
	return "Experience [" +  super.toString() +", expInYear=" + expInYear + ", proSkill=" + proSkill + "]";
    }
    

}
