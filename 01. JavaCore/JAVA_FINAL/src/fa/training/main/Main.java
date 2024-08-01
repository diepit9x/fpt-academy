/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 28, 2024
 */
package fa.training.main;

import java.awt.Choice;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fa.training.dao.MonitorDAO;
import fa.training.dao.MonitorDAOImpl;
import fa.training.entities.Monitor;
import fa.training.service.ReadFileService;
import fa.training.util.Constant;

public class Main {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
	Scanner scanner = null;
	String choice;
	boolean exit = false;
	MonitorDAO monitorDAO = new MonitorDAOImpl();
	
	try {
	    scanner = new Scanner(System.in);
		do {
		    menu();
		    choice = scanner.nextLine();
		    try {
			switch (choice) {
			case "1": {
			    List<Monitor> monitors =  monitorDAO.getAll();
			    for (Monitor monitor : monitors) {
				monitor.showInfo();
			    }
			   break;
			}
			case "2": {
			    break;
			}
			case "3": {
			    
				   break;
				}
			case "4": {
			exit = true;
				   break;
				}
			default:
			    continue;
			}
			
		    } catch (Exception e) {
			System.out.println(Constant.DEFAULT_EXCEPTION);
		    }
		   
		    if (exit) {
			break;
		    }
		} while (true);
	} finally {
	    if (scanner != null) {
		scanner.close();
	    }
	}

    }
    
public static void main2(String[] args) {
    ReadFileService readFileService = new ReadFileService();
    MonitorDAO monitorDAO = new MonitorDAOImpl();
    
//   
//    for (String string :  readFileService.readFile()) {
//	System.out.println(string);
//    }
    
    List<Monitor> monitors = readFileService.convertToMonintor(readFileService.readFile());
    
    try {
//	monitorDAO.addMonitor(monitors);
	
	 List<Monitor>monitors2 = monitorDAO.getAll();
	 
	 for (Monitor monitor : monitors2) {
	    monitor.showInfo();
	}
    } catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    }
    
    
}
    
    
    public static void menu() {
	System.out.println("1. THONG TIN");
	System.out.println("2. CAP NHAT GIA");
	System.out.println("3. xxxx");
	System.out.println("4. EXIT");
	System.out.print("Choose: ");
    }
    
    public static void updatePrice(MonitorDAO monitorDAO, Scanner scanner) throws Exception {
	System.out.print("Nhap type: ");
	int type;
	do {
	    try {
		type = Integer.parseInt(scanner.nextLine());
		    break;
	    } catch (Exception e) {
		System.out.println("type  must be from 1 to 3");
	    }
	} while (true);
	monitorDAO.updateMonitor(type);
	
    }
}
