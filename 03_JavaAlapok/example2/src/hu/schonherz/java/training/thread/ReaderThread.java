package hu.schonherz.java.training.thread;

import java.util.List;

import hu.schonherz.java.training.firereader.EmployeeReader;
import hu.schonherz.java.training.pojo.Employee;

/**
 * Sample custom Thread class.
 * 
 * When ran, it will read employees from a file
 * and print the value of their fields three times.
 */
public class ReaderThread extends Thread {

    @Override
    public void run() {
        int i = 0;

        while (i < 3) {
            i++;

            List<Employee> employees = EmployeeReader.read();
            for (Employee employee : employees) {
                System.out.println(employee.getName() + " - " + employee.getEmployeeID());
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
