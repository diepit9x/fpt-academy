package fa.training.main;

import fa.training.entities.Course;
import fa.training.entities.SearchData;

import java.util.Scanner;

public class CourseManagement {
    private Course[] courses = null;
    public static void main(String[] args) {
	CourseManagement courseManagement = new CourseManagement();
	
	Scanner scanner = null;
	try {
	    scanner = new Scanner(System.in);
	    System.out.println("-----------a-------------");
	    courseManagement.courses = courseManagement.create(scanner);
	    printArr(courseManagement.courses);
	    

	    System.out.println("-----------b-------------");
	    System.out.print("Enter attribute to search by (courseCode, courseName, duration, status, flag): ");
	    String type = scanner.nextLine();
	    System.out.print("Enter search value: ");
	    String keyword = scanner.nextLine();
	    
	    SearchData searchData = new SearchData(courseManagement.courses, keyword);
	    courseManagement.find(type, searchData); 
	    

	    System.out.println("-----------c-------------");
	    searchData.setKeyword("mandatory");
	    type = "flag";
	    courseManagement.find(type, searchData); 

	    
	    
	} finally {
	    if (scanner != null) {
		scanner.close();
	    }
	}
	
    }
    
    public  Course[] create(Scanner scanner){
        System.out.print("n= ");
        int n;
        do {
	    try {
		 n = Integer.parseInt(scanner.nextLine());
		 break;
	    } catch (Exception e) {
		e.printStackTrace();
		System.err.print("Try again:");
	    }
	} while (true);
        Course[] courses = new Course[n];
        for (int i=0; i < n; i++){
            courses[i] = new Course();
            courses[i].input(scanner);
        }
        return courses;
    }
    
    public void find(String type, SearchData searchData) {
	Course[] courses = searchData.getCourse();
	String keyword = searchData.getKeyword();
	if (type != null) {
	    switch (type) {
	    case "courseCode":
		for (Course course : courses) {
		    if (keyword.equals(course.getCourseCode())) {
			System.out.println(course.toString());
		    }
		}
		break;
	    
	    case "courseName": {
		for (Course course : courses) {
		    if (keyword.equals(course.getCourseName())) {
			System.out.println(course.toString());
		    }
		}
		break;
	    }
	    case "duration": {
		try {
		    for (Course course : courses) {
			    if (Double.parseDouble(keyword.toString()) == course.getDuration()) {
				System.out.println(course.toString());
			    }
			}
		} catch (NumberFormatException  e) {
		    System.out.println("invalid data");
		}
		break;
	    }
	    case "status": {
		for (Course course : courses) {
		    if (keyword.equals(course.getStatus())) {
			System.out.println(course.toString());
		    }
		}
		break;
	    }
	    case "flag": {
		for (Course course : courses) {
		    if (keyword.equals(course.getFlag())) {
			System.out.println(course.toString());
		    }
		}
		break;
	    }
	    default:
		System.out.println("type is invalid");
		break;
	    }
	}
    }
    
    public static void printArr(Object[] objects) {
	for (Object object : objects) {
	    System.out.println(object.toString());
	}
    }
    
    
}
