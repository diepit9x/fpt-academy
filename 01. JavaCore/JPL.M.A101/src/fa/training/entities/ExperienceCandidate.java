package fa.training.entities;

import java.io.Serializable;
import java.util.Date;

public class ExperienceCandidate extends Candidate implements Serializable {
    private static final long serialVersionUID = 1L;
    private int yearsExperience;
    private String professionalSkill;
    
    public ExperienceCandidate() {
    }

    public ExperienceCandidate(String firstName, String lastName, Date birthDate, String address, String phone,
	    String email, int yearsExperience, String professionalSkill) {
	super(firstName, lastName, birthDate, address, phone, email);
	this.yearsExperience = yearsExperience;
	this.professionalSkill = professionalSkill;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public String getProfessionalSkill() {
        return professionalSkill;
    }

    public void setProfessionalSkill(String professionalSkill) {
        this.professionalSkill = professionalSkill;
    }

    @Override
    public String toString() {
	return "ExperienceCandidate [" + super.toString() + ", yearsExperience=" + yearsExperience + ", professionalSkill=" + professionalSkill
		+ "]";
    }
    
    public String display() {
	return String.format("%-30s %-30s %-30s %-30s %-30s %-30s %-30d %s",
		getFirstName(), getLastName(), getBirthDate(), getAddress(), getPhone(), getEmail(), getYearsExperience(), getProfessionalSkill());
    }

}
