package fa.training.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.utils.Constant;
import fa.training.utils.Validator;

public class CustomerService2 {
    private List<Customer> customers;

    public CustomerService2() {
	this.customers = read();
    }

    /**
     * Create customer
     * @param scanner
     * @return
     */
    public List<Customer> createCustomer(Scanner scanner) {
	System.out.println("----Enter Customer Information----");
	System.out.print("Enter name:");
	String name = scanner.nextLine();
	System.out.print("Enter phone:");
	String phoneNumber;
	while (true) {
	    phoneNumber = scanner.nextLine();
	    if (Validator.isPhoneNumber(phoneNumber)) {
		break;
	    }
	    System.out.print("Invalid. Try again:");
	}
	System.out.print("Enter address:");
	String address = scanner.nextLine();
	// Order
	System.out.println("Enter order info:");
	List<Order> orders = new ArrayList<>();
	String number;
	Date date;
	while (true) {
	    System.out.print("+ number:");
	    number = scanner.nextLine();
	    System.out.print("+ date:");
	    while (true) {
		date = stringToDate(scanner.nextLine());
		if (date != null) {
		    break;
		}
		System.out.print("Invalid. Try again: ");
	    }
	    orders.add(new Order(number, date));
	    // continue ?
	    System.out.print("Do you want to add more order ? (Y/N): ");
	    if ((scanner.nextLine().toUpperCase()).equals("N")) {
		break;
	    }
	}
	Customer newCustomer = new Customer(name, phoneNumber, address, orders);
	customers.add(newCustomer);
	save(customers);
	return customers;
    }

    /**
     * String to Date
     * @param dateString
     * @return
     */
    private Date stringToDate(String dateString) {
	try {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	    Date date = dateFormat.parse(dateString);
	    return date;
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * Save customer to file
     * @param customers
     */
    public void save(List<Customer> customers) {
	try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Constant.FILE_NAME))) {
	    outputStream.writeObject(customers);
	    // System.out.println("Customer list saved successfully to " + fileName);
	    return;
	} catch (IOException e) {
	    // System.out.println("Failed to save customer list");
	    e.printStackTrace();
	    return;
	}
    }

    /**
     * Read customer from a file;
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Customer> read() {
	try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Constant.FILE_NAME))) {
	    List<Customer> customers = (List<Customer>) inputStream.readObject();
	    // System.out.println("Loaded customers: " + customers.size());
	    return customers;
	} catch (IOException | ClassNotFoundException e) {
	    // System.out.println("Failed to load customer list");
	    e.printStackTrace();
	    return new ArrayList<Customer>();
	}
    }

    /**
     * display data
     */
    public void display() {
	List<Customer> customers = read();
	if (customers == null || customers.isEmpty()) {
	    System.out.println("There is no customer in data");
	    return;
	}
	System.out.println("-----ALL CUSTOMER-----");
	for (Customer customer : customers) {
	    System.out.println(customer.toString());
	}
    }

    /**
     * Get all customers
     * @return
     */
    public List<String> findAll() {
	List<String> stringList = new ArrayList<>();
	for (Customer customer : customers) {
	    stringList.add(customer.toString());
	}
	return stringList;
    }

    /**
     * Find customer
     * @param phone
     */
    public List<Customer> find(String phone) {
	return customers.stream()
		.filter(customer -> customer.getPhoneNumber().equals(phone))
		.toList();
    }

    /**
     * Remove customer by phone numberRemove customer by phone number
     * @param phone
     * @return
     */
    public boolean remove(String phone) {
	for (int i = 0; i < customers.size(); i++) {
	    if (customers.get(i).getPhoneNumber().equals(phone)) {
		customers.remove(i); // Save to file
		save(customers);
		return true;
	    }
	}
	return false;
    }

}
