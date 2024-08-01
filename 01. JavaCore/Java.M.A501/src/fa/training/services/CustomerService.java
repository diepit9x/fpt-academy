package fa.training.services;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.utils.Constant;
import fa.training.utils.Validator;

public class CustomerService {
	Scanner scanner = new Scanner(System.in);
	
    /**
     * Create customer from the keyboard
     * @return
     */
    public List<String> createCustomer() {
	List<String> customers = findAll();

	do {
	    System.out.println("----Enter Customer Information----");
	    System.out.print("Enter name:");
	    String name = scanner.nextLine();
	    System.out.print("Enter phone:");
	    String phoneNumber;
	    while (true) {
		phoneNumber = scanner.nextLine().trim();
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
	    do {
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
	    } while (true);
	    customers.add((new Customer(name, phoneNumber, address, orders)).toString());
	    // continue ?
	    System.out.print("Do you want to add more customer ? (Y/N): ");
	    if ((scanner.nextLine().toUpperCase()).equals("N")) {
		break;
	    }
	} while (true);
	save(customers);
	return customers;
    }
    
    /**
     * String to Date
     * 
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
     * Save customers to file
     * @param customers
     * @return
     */
    public String save(List<String> customers) {
	try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(Constant.FILE_NAME))) {
	    for (String customer : customers) {
		bos.write(customer.getBytes(StandardCharsets.UTF_8));
		bos.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
	    }
	    return Constant.SUCCESS;
	} catch (IOException e) {
	    e.printStackTrace();
	    return Constant.FAIL;
	}
    }

    /**
     * get all of customers
     * @return
     */
    public List<String> findAll() {
        List<String> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Constant.FILE_NAME))) {
            String customer;
            while ((customer = br.readLine()) != null) {
        	customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
    
    /**
     * display data
     * @param customers
     */
    public void display(List<String> customers) {
	System.out.println(
		    String.format("%-30s %-30s %-30s %s", "Customer Name", "Phone Number", "Address", "OrderList")
		    );
	for (String customer : customers) {
	    String[] str = customer.split("(?<=, )[pao]");
	    for (int i = 0; i < str.length; i++) {
		str[i] = str[i].substring(str[i].indexOf('=') +1 );
		str[i] = str[i].replaceAll(",\\s*$", "");
	    }
	    System.out.println(
		    String.format("%-30s %-40s %-40s %s", str[0], str[1], str[2], str[3])
		    );
	}
    }
    
    /**
     * Search customer by phone
     * @param phone
     * @return
     */
    public List<String> search(String phone) {
	List<String> findAll = findAll();
	List<String> resultList = new ArrayList<>();
	if (!findAll.isEmpty()) {
	    for (int i = 0; i < findAll.size(); i++) {
		if (findAll.get(i).contains("phoneNumber="+phone.trim())) {
		    resultList.add(findAll.get(i));
		}
	    }
	}
	return resultList;
    }
    
    /**
     * Remove customer by phone
     * @param phone
     * @return
     */
    public boolean remove(String phone) {
	List<String> search = search(phone);
	List<String> customers = findAll();
	if (search.isEmpty()) {
	    return false;
	} else {
	    for (String customer : search) {
		customers.remove(customer);
	    }
	    save(customers);
	    return true;
	}
    }
}
