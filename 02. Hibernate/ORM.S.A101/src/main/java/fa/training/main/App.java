package fa.training.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;

import fa.training.dto.ApartmentDAO;
import fa.training.dto.BillDAO;
import fa.training.dto.CustomerDAO;
import fa.training.dto.EmployeeDAO;
import fa.training.entities.Apartment;
import fa.training.entities.Bill;
import fa.training.entities.Customer;
import fa.training.entities.Employee;
import fa.training.exception.BillException;
import fa.training.exception.DataAlreadyExistException;

public class App {
	private static EmployeeDAO employeeDAO = new EmployeeDAO();
	private static CustomerDAO customerDAO = new CustomerDAO();
	private static ApartmentDAO apartmentDAO = new ApartmentDAO();
	private static BillDAO billDAO = new BillDAO();

	public static void main(String[] args) throws HibernateException, DataAlreadyExistException, BillException {
		App app = new App();
		Scanner scanner = null;
		boolean exit = false;
		String choice;
		try {
			scanner = new Scanner(System.in);
			app.insertData();
			
			do {
				menu();
				choice = scanner.nextLine();
				
				switch (choice) {
				case "1": {
					try {
						System.out.println("========CUSTOMER LIST=========");
						customerDAO.getAllCustomers().forEach(e -> System.out.println(e.toString()));
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				}
				case "2": {
					try {
						System.out.println("========EMPLOYEE LIST=========");
						employeeDAO.getAllEmployees().forEach(e -> System.out.println(e.toString()));
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				}
				case "3": {
					try {
						System.out.println("========APARTMENT LIST=========");
						apartmentDAO.getAllApartments().forEach(e -> System.out.println(e.toString()));
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				}
				case "4": {
					try {
						System.out.println("========BILL LIST=========");
						billDAO.getAllBills().forEach(b -> System.out.println(b.toString()));
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				}
				case "5": {
					System.out.println("========SEARCH	APARTMENT=========");
					try {
						app.searchApartment(scanner);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case "6": {
					exit = true;
					break;
				}
				default:
					System.err.println("lua chon khong hop le");
					continue;
				}
				
				if (exit) {
					break;
				}
			} while (true);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	
	public static void menu() {
		System.out.println("1. Show all customers");
		System.out.println("2. Show all employees");
		System.out.println("3. Show all Apartment");
		System.out.println("4. Show all Bills");
		System.out.println("5. Search apartment");
		System.out.println("6. Exit");
		System.out.print("Choice: ");
	}
	
	
	public void searchApartment(Scanner scanner) {
		Integer employeeId, customerId, apartmentId, numBedroom;
		Employee employee = null;
		Apartment apartment = null;
		Customer customer = null;
		
		System.out.print("Nhap so phong ngu: ");
		numBedroom = Integer.parseInt(scanner.nextLine());
		
		List<Apartment> apartments = apartmentDAO.findByNumBedroom(numBedroom);
		if (apartments.isEmpty()) {
			System.out.println("Hien tai da het can ho phu hop voi khach hang");
			return;
		}
		System.out.println("-------DANH SACH LUA CHON---------");
		apartments.forEach(a -> System.out.println(a.toString()));
		System.out.println("--------------------------------------------");
		
		System.out.print("Nhap apartmentId: ");
		do {
			try {
				apartmentId = Integer.parseInt(scanner.nextLine());
				apartment = apartmentDAO.getApartmentById(apartmentId);
				if (apartment != null && apartments.contains(apartment)) {
					break;
				} else {
					System.err.print("ApartmentId khong hop le. Nhap lai: ");
				}
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		} while (true);
		
		System.out.print("Nhap employeeId: ");
		do {
			try {
				employeeId = Integer.parseInt(scanner.nextLine());
				employee = employeeDAO.getEmployeeById(employeeId);
				if (employee != null) {
					break;
				} else {
					System.err.print("EmployeeId khong hop le. Nhap lai: ");
				}
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		} while (true);
		
		System.out.print("Nhap customerId: ");
		do {
			try {
				customerId = Integer.parseInt(scanner.nextLine());
				customer = customerDAO.getCustomerById(customerId);
				if (customer != null) {
					break;
				} else {
					System.err.print("customerId khong hop le. Nhap lai: ");
				}
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		} while (true);
		
		Bill bill = new Bill();
		bill.setApartment(apartment);
		bill.setCustomer(customer);
		bill.setEmployee(employee);
		bill.setPrice(apartment.getPrice());
		bill.setBuyDate(LocalDate.now());
		
		try {
			billDAO.insertBill(bill);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void insertData() throws HibernateException, DataAlreadyExistException, BillException {
		Employee e = new Employee();
		for (int i = 1; i <= 5; i++) {
			e.setEmployeeName("emp" + i);

			if (i == 1) {
				e.setRole("QL");
			} else {
				e.setRole("NV");
			}
			employeeDAO.insertEmployee(e);
		}

		Customer c = new Customer();
		for (int i = 1; i <= 5; i++) {
			c.setCustomerName("customer" + i);
			customerDAO.insertCustomer(c);
		}

		Apartment ap = new Apartment();
		ap.setDoorDirection("north");
		for (int i = 1; i <= 3; i++) {
			ap.setAppartmentCode("app" + i);
			ap.setNumberBedroom(1);
			ap.setStatus("chua ban");
			ap.setPrice(1000D + i *122D);
			apartmentDAO.insertApartment(ap);
		}

		for (int i = 4; i <= 8; i++) {
			ap.setAppartmentCode("app" + i);
			ap.setNumberBedroom(2);
			ap.setStatus("chua ban");
			apartmentDAO.insertApartment(ap);
		}

		for (int i = 9; i <= 10; i++) {
			ap.setAppartmentCode("app" + i);
			ap.setNumberBedroom(3);
			ap.setStatus("chua ban");
			apartmentDAO.insertApartment(ap);
		}

//		 [BILL]: gồm 6 đơn hàng đã được bán (có 1 khách hàng mua 2 căn chung cư khác nhau).
		Bill bill1 = new Bill();
		bill1.setApartment(apartmentDAO.getApartmentById(2));
		bill1.setCustomer(customerDAO.getCustomerById(1));
		bill1.setEmployee(employeeDAO.getEmployeeById(2));
		bill1.setPrice(apartmentDAO.getApartmentById(2).getPrice());

		Bill bill2 = new Bill();
		bill2.setApartment(apartmentDAO.getApartmentById(3));
		bill2.setCustomer(customerDAO.getCustomerById(1));
		bill2.setEmployee(employeeDAO.getEmployeeById(2));
		bill2.setPrice(apartmentDAO.getApartmentById(3).getPrice());

		Bill bill3 = new Bill();
		bill3.setApartment(apartmentDAO.getApartmentById(4));
		bill3.setCustomer(customerDAO.getCustomerById(2));
		bill3.setEmployee(employeeDAO.getEmployeeById(2));
		bill3.setPrice(apartmentDAO.getApartmentById(4).getPrice());

		Bill bill4 = new Bill();
		bill4.setApartment(apartmentDAO.getApartmentById(5));
		bill4.setCustomer(customerDAO.getCustomerById(3));
		bill4.setEmployee(employeeDAO.getEmployeeById(3));
		bill4.setPrice(apartmentDAO.getApartmentById(5).getPrice());

		Bill bill5 = new Bill();
		bill5.setApartment(apartmentDAO.getApartmentById(6));
		bill5.setCustomer(customerDAO.getCustomerById(4));
		bill5.setEmployee(employeeDAO.getEmployeeById(3));
		bill5.setPrice(apartmentDAO.getApartmentById(6).getPrice());

		Bill bill6 = new Bill();
		bill6.setApartment(apartmentDAO.getApartmentById(7));
		bill6.setCustomer(customerDAO.getCustomerById(5));
		bill6.setEmployee(employeeDAO.getEmployeeById(4));
		bill6.setPrice(apartmentDAO.getApartmentById(7).getPrice());

		billDAO.insertBill(bill1);
		billDAO.insertBill(bill2);
		billDAO.insertBill(bill3);
		billDAO.insertBill(bill4);
		billDAO.insertBill(bill5);
		billDAO.insertBill(bill6);
	}

}
