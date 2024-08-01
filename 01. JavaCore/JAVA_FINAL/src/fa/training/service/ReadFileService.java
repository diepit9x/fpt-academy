/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 28, 2024
 */
package fa.training.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.GamingMonitor;
import fa.training.entities.GraphicsMonitor;
import fa.training.entities.Monitor;
import fa.training.entities.NormalMonitor;
import fa.training.exception.BrandException;
import fa.training.exception.IncorrectFormatException;
import fa.training.util.Constant;

public class ReadFileService {

    public List<String> readFile() {
	    List<String> strings = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(Constant.FILE_NAME))) {
		String str;
		while ((str = br.readLine()) != null) {
		    strings.add(str);
		}
	    } catch (IOException e) {
		System.err.println(e.getMessage());
	    }
	    return strings;
}
    
    /**
     * Convert to monintor.
     *
     * @param strings the strings
     * @return the list
     */
    public List<Monitor> convertToMonintor(List<String> strings) {
	List<Monitor> monitors = new ArrayList<>();
	Monitor monitor;
	String[] elementString;
	for (int i = 0; i < strings.size(); i++) {
	    elementString = strings.get(i).split(",");
	    try {
		if (elementString[0].equals("1")) {
		    monitor = new NormalMonitor();
		} else if (elementString[0].equals("2")) {
		    monitor = new GraphicsMonitor();
		} else if (elementString[0].equals("3")) {
		    monitor = new GamingMonitor();
		} else {
		    System.out.println("Type of Line "+(i+1)+" is invalid");
		    continue;
		}
		
		monitor.setType(Integer.parseInt(elementString[0]));
		try {
			monitor.setMonitorId(elementString[1]);
			for (Monitor checkMonitor: monitors) {
			    if (monitor.getMonitorId().equals(checkMonitor.getMonitorId())) {
				System.out.println("Monitor "+monitor.getMonitorId()+" has duplicate");
				continue;
			    }
			}
		} catch (IncorrectFormatException e) {
		    System.out.println("MonitorId of row "+(i+1)+" is incorrect");
		    continue;
		}
		monitor.setName(elementString[2]);
		try {
		    monitor.setBrand(elementString[3]);
		} catch (BrandException e) {
		    System.out.println(e.getMessage());
		    continue;
		}

		monitor.setSize(elementString[4]);
		monitor.setResolution(elementString[5]);
		monitor.setPanel(elementString[6]);
		monitor.setImportDate(Date.valueOf(elementString[7]));
		monitor.setWarrantYear(Integer.parseInt(elementString[8]));
		monitor.setPrice(Integer.parseInt(elementString[9]));
		
		if (monitor.getType() == 1) {
		    ((NormalMonitor) monitor).setTouchScreen(elementString[10]);
		} else if (monitor.getType() == 2) {
		    ((GraphicsMonitor) monitor).setsRGB(elementString[11]);
		    ((GraphicsMonitor) monitor).setAdobeRGB(elementString[12]);
		} else {
		    ((GamingMonitor) monitor).setRefreshRate(elementString[13]);
		    ((GamingMonitor) monitor).setResponseTime(elementString[14]);
		}
		monitors.add(monitor);
	    } catch (Exception e) {
		System.out.println(Constant.DEFAULT_EXCEPTION);
	    }
	}
	
	return monitors;
	
    }
    
    
    public static void main(String[] args) {
	ReadFileService readFileService = new ReadFileService();
	List<String> strs = readFileService.readFile();
	List<Monitor> monitors = readFileService.convertToMonintor(strs);
	
	for (Monitor monitor : monitors) {
	    System.out.println(monitor.toString());
	}
    }

}
