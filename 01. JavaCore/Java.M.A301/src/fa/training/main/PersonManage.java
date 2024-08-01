package fa.training.main;

import java.util.Scanner;

import fa.training.entities.Person;
import fa.training.entities.Student;
import fa.training.entities.Teacher;
import fa.training.utils.Validator;

public class PersonManage {
    private Person[] persons;

    public static void main(String[] args) {
	PersonManage personManage = new PersonManage();

	Scanner scanner = null;
	try {
	    scanner = new Scanner(System.in);
	    String choice;
	    boolean exit = false;
	    while (true) {
		personManage.menu();
		System.out.print("Select an option: ");
		choice = scanner.nextLine();
		switch (choice) {
		case "1":
		    personManage.persons = personManage.inputArrayData(scanner);
		    break;
		case "2":
		    personManage.updateStudent(scanner);
		    break;
		case "3":
		    personManage.displayAll();
		    break;
		case "4":
		    personManage.displayTeacher();
		    break;
		case "5":
		    personManage.displayStudent();
		    break;
		case "6":
		    exit = true;
		    break;
		default:
		    System.out.println("Your choice is invalid. Try again");;
		    break;
		}
		if (exit) {
		    break;
		}
	    }

	} finally {
	    if (scanner != null) {
		scanner.close();
	    }
	}

    }

    /**
     * input array data
     * @param scanner
     * @return
     */
    public Person[] inputArrayData(Scanner scanner) {
	int n;
	System.out.print("n= ");
	n = Integer.parseInt(scanner.nextLine());
	Person[] persons = new Person[n];
	for (int i = 0; i < persons.length; i++) {
	    persons[i] = inputData(scanner);
	}
	return persons;
    }

    /**
     * Input a person from the keyboard
     * @param scanner
     * @return
     */
    public Person inputData(Scanner scanner) {
	int personType;
	String name, gender, phoneNumber, email, studentId;
	double theory, practice, bassicSalary, subsidy;
	Person person;

	// Choose type person
	System.out.println("1. Student");
	System.out.println("2. Teacher");
	System.out.print("Choose person type: ");
	personType = Integer.parseInt(scanner.nextLine());

	/* Input data form the keyboard */
	// name
	System.out.print("Name: ");
	name = scanner.nextLine();
	// gender
	System.out.print("Gender: ");
	gender = scanner.nextLine();
	// phone number
	System.out.print("PhoneNumber: ");
	phoneNumber = scanner.nextLine();
	// Email
	System.out.print("Email: ");
	while (true) {
	    email = scanner.nextLine();
	    if (Validator.isEmail(email)) {
		break;
	    }
	    System.out.print("Invalid. Try again: ");
	}
	/* choose person type */
	if (personType == 1) {
	    // StudentId
	    System.out.print("studentID: ");
	    studentId = scanner.nextLine();

	    System.out.print("Theory: ");
	    while (true) {
		theory = Double.parseDouble(scanner.nextLine());
		if (Validator.numberRange(theory)) {
		    break;
		}
		System.out.print("Invalid. Try again: ");
	    }

	    System.out.print("Practice: ");
	    while (true) {
		practice = Double.parseDouble(scanner.nextLine());
		if (Validator.numberRange(practice)) {
		    break;
		}
		System.out.print("Invalid. Try again: ");
	    }
	    person = new Student(name, gender, phoneNumber, email, studentId, theory, practice);
	} else {
	    // Teacher
	    System.out.print("Bassic salary: ");
	    while (true) {
		bassicSalary = Double.parseDouble(scanner.nextLine());
		if (bassicSalary > 0) {
		    break;
		}
		System.out.print("Invalid. Try again: ");
	    }
	    System.out.print("Subsidy: ");
	    while (true) {
		subsidy = Double.parseDouble(scanner.nextLine());
		if (subsidy > 0) {
		    break;
		}
		System.out.print("Invalid. Try again: ");
	    }
	    person = new Teacher(name, gender, phoneNumber, email, bassicSalary, subsidy);

	}
	return person;
    }

    /**
     * update student info by entering studentId.
     * @param scanner
     */
    public void updateStudent(Scanner scanner) {
	if (this.persons != null) {
	    return;
	}
	System.out.print("studentID= ");
	String studentId = scanner.nextLine();

	for (int i = 0; i < persons.length; i++) {
	    if (persons[i] instanceof Student) {
		if (((Student) persons[i]).getStudentId().equals(studentId)) {
		    persons[i] = inputData(scanner);
		    return;
		}
	    }
	}
	System.out.println("there is no student with studentId = " + studentId);
    }

    /**
     * Display all
     */
    public void displayAll() {
	if (this.persons != null) {
	    for (Person person : this.persons) {
		System.out.println(person.toString());
	    }
	}
    }

    /**
     * displays information about teachers, who has a salary higher than 1000$.
     */
    public void displayTeacher() {
	for (int i = 0; i < persons.length; i++) {
	    if (persons[i] instanceof Teacher) {
		if (((Teacher) persons[i]).calculateSalary() > 1000D) {
		    System.out.println(persons[i].toString());
		}
	    }
	}
    }

    /**
     * display all students and their final mark, who qualify to pass the course (final mark >= 6)
     */
    public void displayStudent() {
	for (int i = 0; i < persons.length; i++) {
	    if (persons[i] instanceof Student) {
		if (((Student) persons[i]).calculateFinalMark() >= 6.0D) {
		    System.out.println(persons[i].toString());
		}
	    }
	}
    }
    
    /**
     * Menu
     */
    public void menu() {
	System.out.println("==============");
	System.out.println("1. input data");
	System.out.println("2. Update student");
	System.out.println("3. Display all");
	System.out.println("4. Display Teacher");
	System.out.println("5. Report student");
	System.out.println("6. Exit");
	System.out.print("Choice: ");
    }

}
