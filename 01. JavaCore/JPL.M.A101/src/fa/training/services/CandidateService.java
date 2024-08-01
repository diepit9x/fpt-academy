package fa.training.services;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fa.training.entities.Candidate;
import fa.training.entities.ExperienceCandidate;
import fa.training.entities.FresherCandidate;
import fa.training.util.Constant;

public class CandidateService {
    private List<String> candidates;
    private final Object lock = new Object();

    public CandidateService() throws InterruptedException {
	candidates = readFile();
    }

    /**
     * add candidate
     * 
     * @param scanner
     */
    public void addCandidate(Scanner scanner) {
	synchronized (lock) {
	    Candidate candidate = null;
	    String firstName, lastName, address, phone, email, graduationRank, education, professionalSkill, choice;
	    int yearsExperience = 0;
	    Date birthDate, graduationDate;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	    System.err.println("----Add a new candidate----");
	    System.out.println("1. FresherCandidate");
	    System.out.println("2. ExperienceCandidate");
	    System.out.print("Choice: ");
	    choice = scanner.nextLine();
	    // data
	    System.out.print("firstName: ");
	    firstName = scanner.nextLine();
	    System.out.print("lastName: ");
	    lastName = scanner.nextLine();
	    System.out.print("birthDate(dd/MM/yyyy: ");
	    do {
		try {
		    birthDate = dateFormat.parse(scanner.nextLine());
		    break;
		} catch (ParseException e) {
		    e.printStackTrace();
		    System.out.print("Try again: ");
		}
	    } while (true);
	    System.out.print("address: ");
	    address = scanner.nextLine();
	    System.out.print("phone: ");
	    phone = scanner.nextLine();
	    System.out.print("email: ");
	    email = scanner.nextLine();

	    if (choice.equals("1")) {
		System.out.print("graduationDate(dd/MM/yyyy: ");
		do {
		    try {
			graduationDate = dateFormat.parse(scanner.nextLine());
			break;
		    } catch (ParseException e) {
			e.printStackTrace();
			System.out.print("Try again: ");
		    }
		} while (true);
		System.out.print("graduationRank: ");
		graduationRank = scanner.nextLine();
		System.out.print("education: ");
		education = scanner.nextLine();
		// initialize
		candidate = new FresherCandidate(firstName, lastName, birthDate, address, phone, email, graduationDate,
			graduationRank, education);
	    } else {
		System.out.print("yearsExperience: ");
		do {
		    try {
			yearsExperience = Integer.parseInt(scanner.nextLine());
			break;
		    } catch (Exception e) {
			System.out.print("Invalid. Try again:");
		    }
		} while (true);
		System.out.print("professionalSkill: ");
		professionalSkill = scanner.nextLine();
		// initialize
		candidate = new ExperienceCandidate(firstName, lastName, birthDate, address, phone, email,
			yearsExperience, professionalSkill);
	    }
	    // add candidate to list
	    candidates.add(candidate.toString());
	    writeFile(candidates);
	    System.out.println("Add candidate successfully");
	}
    }

    /**
     * Save candidate to file
     * 
     * @param candidates
     */
    public void writeFile(List<String> candidates) {
	synchronized (lock) {
	    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(Constant.FILE_NAME))) {
		for (String candidate : candidates) {
		    bos.write(candidate.getBytes(StandardCharsets.UTF_8));
		    bos.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
		}
		System.out.println(Constant.SUCCESS);
	    } catch (IOException e) {
		e.printStackTrace();
		System.out.println(Constant.FAIL);
	    }
	}
    }

    /**
     * Read candidates from file
     * 
     * @return
     */
    public List<String> readFile() {
	synchronized (lock) {
	    List<String> candidates = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(Constant.FILE_NAME))) {
		String candidate;
		while ((candidate = br.readLine()) != null) {
		    candidates.add(candidate);
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return candidates;
	}
    }

    /**
     * Display candidate
     */
    public void displayCandidate() {
	candidates = readFile();
	List<Map<String, String>> candidateList = new ArrayList<>();
	System.out.println("----Display table -----");
	for (String candidate : candidates) {
	    candidateList.add(stringToTable(candidate));
	}

	for (Map<String, String> candidate : candidateList) {
	    System.out.println(candidate);
	}
    }

    private Map<String, String> stringToTable(String candidate) {
	// create
	Map<String, String> map = new HashMap<>();

	int startIndex = candidate.indexOf('[');
	int endIndex = candidate.indexOf(']');

	if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
	    // Lay ben trong [ ... ]
	    String insideBrackets = candidate.substring(startIndex + 1, endIndex);

	    // Tach chuoi
	    String[] pairs = insideBrackets.split(",\\s*");

	    // them vao map
	    for (String pair : pairs) {
		String[] keyValue = pair.split("=");
		if (keyValue.length == 2) {
		    String key = keyValue[0].trim();
		    String value = keyValue[1].trim();
		    map.put(key, value);
		}
	    }
	}
	return map;
    }

}