package fa.training.entities;

public class Teacher extends Person {
    private double bassicSalary;
    private double subsidy;
    
    public Teacher() {
	super();
    }

    public Teacher(String nameString, String genderString, String phoneNumberString, String email, double bassicSalary,
	    double subsidy) {
	super(nameString, genderString, phoneNumberString, email);
	this.bassicSalary = bassicSalary;
	this.subsidy = subsidy;
    }
    public double getBassicSalary() {
        return bassicSalary;
    }

    public void setBassicSalary(double basicSalary) {
        this.bassicSalary = basicSalary;
    }

    public double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(double subsidy) {
        this.subsidy = subsidy;
    }
    
    public double calculateSalary() {
	return bassicSalary + subsidy;
    }

    @Override
    public String toString() {
	return "Teacher [bassicSalary=" + bassicSalary + ", subsidy=" + subsidy + ", " + super.toString()
		+ "]";
    }


    
    
}
