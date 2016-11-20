package hu.schonherz.java.training.pojo;

import java.util.List;

public class Developer extends Employee {
    private static final long serialVersionUID = -3936601396418860868L;

    private List<String> tasks;

    // Example:
    // overriding the name attribute of superclass
    //private int name;

    // Redefining parameterless constructor
    public Developer() {
        super();
    }

    public Developer(String name, int employeeID, List<String> tasks) {
        super(name, employeeID);
        this.tasks = tasks;
    }

    // Example:
    // Overriding a method of the superclass
    /*@Override
    public void setName(final String name) {
        // Employee setName method will be used
        super.setName(name);
    
        // could not modify the name, cause the final modifier
        // name = "Noa name";
    
        // access the super class attribute
        super.name = name;
        // access this class attribute
        this.name = 123;
    }*/

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

}
