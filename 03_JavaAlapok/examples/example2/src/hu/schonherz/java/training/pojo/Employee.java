package hu.schonherz.java.training.pojo;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 7950521176579703924L;
    protected String name;
    protected int employeeID;
  
    public Employee() {
        super();
    }

    public Employee(String name, int employeeID) {
        super();
        this.setName(name);
        this.setEmployeeID(employeeID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
  
}
