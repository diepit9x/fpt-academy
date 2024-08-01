package fa.training.entities;

public abstract class Person {
    private String name;
    private String gender;
    private String phoneNumber;
    private String email;
    
    public Person() {
    }
    
    public Person(String name, String gender, String phoneNumber, String email) {
	super();
	this.name = name;
	this.gender = gender;
	this.phoneNumber = phoneNumber;
	this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void purchaseParkingPass() {
    }

    @Override
    public String toString() {
	return "name=" + name + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", email=" + email;
    }
    

}
