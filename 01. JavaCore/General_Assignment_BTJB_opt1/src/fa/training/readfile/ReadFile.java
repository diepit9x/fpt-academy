package fa.training.readfile;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.CollationKey;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fa.training.exception.EmailException;
import fa.training.util.DBUtils;
import fa.training.util.PhoneException;

public class ReadFile {
    private static final String FILE_NAME_IN = "src/fa/training/readfile/STD.csv";
    private static final String FILE_NAME_OUT = "src/fa/training/readfile/error.txt";
    
    
    public List<String> readFile() {
	    List<String> students = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME_IN))) {
		String student;
		while ((student = br.readLine()) != null) {
		   students.add(student);
		}
	    } catch (IOException e) {
		System.err.println(e.getMessage());
	    }
	    return students;
    }
    
    public List<Student> convertStudent(List<String> studentString) {
	List<Student>students = new ArrayList<>();
	List<String> errors = new ArrayList<>();
	String[] stdArr;
	Student student;
	StringBuilder error;
	for (int i = 0; i < studentString.size(); i++) {
	    student = new Student();
	    error  = new StringBuilder();
	    stdArr = studentString.get(i).split(",");
	    student.setStdNo(stdArr[0]);
	    student.setStdName(stdArr[1]);
	    try {
		student.setPhone(stdArr[2]);
	    } catch (PhoneException e) {
		error.append("StdPhone");
	    }
	    try {
		student.setEmail(stdArr[3]);
	    } catch (EmailException e) {
		if (!error.isEmpty()) {
		    error.append(", StdEmail");
		} else {
		    error.append("StdEmail");
		}
		
	    }
	    try {
		student.setGradePoint(stdArr[4]);
	    } catch (NumberFormatException e) {
		if (!error.isEmpty()) {
		    error.append(", GradePoint");
		} else {
		    error.append("GradePoint");
		}
	    }
	    //check
	    if (!error.isEmpty()) {
		errors.add("Line " + (i +1) + ": co loi sai dinh dang " + error.toString());
	    } else {
		students.add(student);
	    }
	}
	if (!errors.isEmpty()) {
	    writeFile(errors);
	}
	return students;
    }
    
    public boolean writeFile(List<String> strings) {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME_OUT, StandardCharsets.UTF_8))) {
	        for (String str : strings) {
	            bw.write(str);
	            bw.newLine();
	        }
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
    

    public static void main(String[] args) {
	ReadFile readFile = new ReadFile();
	
	List<Student> students = readFile.convertStudent(readFile.readFile());
	
	for (Student student : students) {
	    System.out.println(student.toString());
	}
//	
//	System.out.println("--------sap xep----------");
//	Collections.sort(students, new Comparator<Student>() {
//
//	    @Override
//	    public int compare(Student o1, Student o2) {
//		if (o1.getStdName().equals(o2.getStdName())) {
//		    return o1.getGradePoint() - o2.getGradePoint();
//		}
//		return o1.getStdName().compareTo(o2.getStdName());
//	    }
//	});
//	
//	for (Student student : students) {
//	    System.out.println(student.toString());
//	}
//	
//	Connection connection = null;
//	PreparedStatement preparedStatement = null;
////	ResultSet resultSet = null;
//	
//	try {
//	    connection = DBUtils.getInstance().getConnection();
//	    preparedStatement = connection.prepareStatement("INSERT INTO Student(stdNo, stdName, phone, email, gradePoint) VALUES(?, ?, ?, ?, ?)");
//	    for (Student student : students) {
//		preparedStatement.setString(1, student.getStdNo());
//		preparedStatement.setString(2, student.getStdName());
//		preparedStatement.setString(3, student.getPhone());
//		preparedStatement.setString(4, student.getEmail());
//		preparedStatement.setInt(5, student.getGradePoint());
//		preparedStatement.addBatch();
//	    }
//	    int[]  check = preparedStatement.executeBatch();
//	    
//	    for (int i : check) {
//		System.out.println(i);
//	    }
	     
//	    
//	    String sreString = new String();
//	    sreString = sreString.concat("123");
//	    System.out.println(sreString);
	    
//	    
//	} catch (SQLException e) {
//	    e.printStackTrace();
//	} finally {
//	    
//	}
	
	
	
	
    }

}
