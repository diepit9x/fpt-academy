package fa.training.services;

//import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileIOService {


//    public  boolean write1(String filename,List<String> strings) {
//	try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename))) {
//	    for (String str : strings) {
//		bos.write(str.getBytes(StandardCharsets.UTF_8));
//		bos.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
//	    }
//	    return true;
//	} catch (IOException e) {
//	    e.printStackTrace();
//	    return false;
//	}
//    }
    
    /**
     * Save airplane  to file
     * @param airplanes
     * @return
     */
    public boolean write(String filename, List<String> strings) {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, StandardCharsets.UTF_8))) {
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
    
    /**
     * read file
     * @return
     */
    public List<String> read(String filename) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String customer;
            while ((customer = br.readLine()) != null) {
        	strings.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}