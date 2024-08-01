package fa.training.entities;

import java.util.Scanner;

import fa.training.utils.Validator;

public class InputData {
    private  Scanner scanner;
    
    public InputData(Scanner scanner) {
	this.scanner = scanner;
    }
    
    /**
     * Input source code
     * @return
     */
    public String courseCode() {
	 System.out.print("courseCode: ");
         String code = scanner.nextLine();
         if (!Validator.courseCode(code)){
             System.out.println("course code is a string of 5 characters, started by FW and followed by 3 digits");
             return courseCode();
         }
         return code;
    }

    /**
     * Input code name;
     * @return
     */
    public String courseName() {
        System.out.print("courseName: ");
        String name = scanner.nextLine();
        if(name == null || name.isEmpty()) {
            System.out.println("Course name is not empty");
            return courseName();
        }
        return name;
    }

    /**
     * Input duration
     * @return
     */
    public double duration() {
        try {
            System.out.print("duration: ");
            String durationString = scanner.nextLine();
            double duration = Double.parseDouble(durationString);
            if (duration <= 0) {
                System.out.println("Duration must be > 0");
                return duration();
            }
            return duration;
        }catch (Exception e){
            System.out.println("Duration is invalid");
            return duration();
        }
    }

    /**
     * Input status
     * @return
     */
    public String status() {
        System.out.print("Status: ");
        String status = scanner.nextLine();
        if (!Validator.status(status)){
            System.out.println("status: only accept active or in-active.");
            return status();
        }
        return status;
    }

    /**
     * Input flag
     * @return
     */
    public String flag() {
        System.out.print("Flag: ");
        String flag = scanner.nextLine();
        if (!Validator.flag(flag)){
            System.out.println("flag: only accept optional, mandatory, N/A.");
            return flag();
        }
        return flag;
    }
}
