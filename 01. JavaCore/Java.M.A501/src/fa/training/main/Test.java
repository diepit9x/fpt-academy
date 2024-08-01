package fa.training.main;

import java.util.List;
import java.util.Scanner;

import fa.training.services.CustomerService;
import fa.training.utils.Constant;
import fa.training.utils.Validator;

public class Test {
    public static void main(String[] args) {
	CustomerService customerService = new CustomerService();
	Test test = new Test();
	Scanner scanner = null;
	String choice;
	boolean exit = false;
	try {
	    scanner = new Scanner(System.in);
	    while (true) {
		test.menu();
		choice = scanner.nextLine();
		switch (choice) {
		case Constant.ADD_CUSTOMER:
		    customerService.createCustomer();
		    break;
		case Constant.SHOW_ALL:
		    test.showall(customerService);
		    break;
		case Constant.SEARCH:
		    test.search(customerService, scanner);
		    break;
		case Constant.REMOVE:
		    test.remove(customerService, scanner);
		    break;
		case Constant.EXIT:
		    exit = true;
		    break;
		default:
		    continue;
		}
		if (exit) {
		    System.out.println("Exited");
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
     * Show all customers
     * @param customerService
     */
    public void showall(CustomerService customerService) {
	List<String> customers = customerService.findAll();
	System.out.println("-----List of customers----");
	for (String customer : customers) {
	    System.out.println(customer);
	}
    }
    
    /**
     * search order by customer's phone
     * @param scanner
     */
    public void search(CustomerService customerService, Scanner scanner) {
	System.out.println("---- Search Customer ----");
	System.out.print("phone: ");
	String phone;
	do {
	    phone = scanner.nextLine().trim();
	    if (Validator.isPhoneNumber(phone)) {
		break;
	    }
	    System.out.print("Invalid. Try again: ");
	} while (true);
	customerService.display(customerService.search(phone));
    }

    /**
     * Remove a specific customer by phone number
     * @param scanner
     */
    public void remove(CustomerService customerService, Scanner scanner) {
	System.out.println("---- Remove Customer by Phone ----");
	System.out.print("phone: ");
	String phone;
	do {
	    phone = scanner.nextLine().trim();
	    if (Validator.isPhoneNumber(phone)) {
		break;
	    }
	    System.out.print("Invalid. Try again: ");
	} while (true);
	boolean isRemove =  customerService.remove(phone);
	if (isRemove) {
	    System.out.println("Delete customer successfully");
	} else {
	    System.out.println("This phone does not exist");
	}
    }

    /**
     * Menu
     */
    public void menu() {
	System.out.println("=====MENU====");
	System.out.println("Choose function:");
	System.out.println("1. Add a new customer");
	System.out.println("2. Show all customer");
	System.out.println("3. Search customer");
	System.out.println("4. Remove customer");
	System.out.println("5. Exit");
	System.out.print("Your choice: ");
    }
}
