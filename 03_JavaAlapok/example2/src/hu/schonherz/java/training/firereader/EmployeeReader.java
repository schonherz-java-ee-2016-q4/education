package hu.schonherz.java.training.firereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import hu.schonherz.java.training.pojo.Employee;

/**
 * Utility class providing static methods
 * to read the employee data from text and binary files
 * under the /files subdirectory.
 */
public class EmployeeReader {

    private static final String SUBDIRECTORY = "files";
    private static final String FILENAME = "employees.txt";

    // This won't work under a Linux system, because it uses '/' as a path separator.
    // private static File file = new File(SUBDIRECTORY + "\" + FILENAME);
    
    // In Java, use File.separator when dealing with paths to ensure compatibility between operating systems.
    private static File file = new File(SUBDIRECTORY + File.separator + FILENAME);
  
    /**
     * @return List of employees which has been found in employees.txt file
     */
    public static List<Employee> read() {
        List<Employee> result = new LinkedList<Employee>();

        /* Alternative solution
        Scanner sc = new Scanner(file);
        while(sc.hasNext()) {
            // do stuff
        }*/

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length < 2) {
                    throw new MyException();
                }
                
                Employee employee = new Employee(attributes[0], Integer.parseInt(attributes[1]));
                result.add(employee);
            }
        } catch (IOException e) {
            System.out.println("File is not found");
        } catch (MyException e) {
            System.out.println("File is corruptd");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("BufferedReader was not closed");
                }
            }
        }

        return result;
    }

}
