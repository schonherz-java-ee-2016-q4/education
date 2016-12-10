package hu.schonherz.training.jdbc.records;

/**
 * Transfer Object
 */

public class Student {

    private int id;
    private String firstName;
    private String lastName;

    public Student(int _id, String _firstName, String _lastName) {
        id = _id;
        firstName = _firstName;
        lastName = _lastName;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String _lastName) {
        lastName = _lastName;
    }

    public String toString() {
        return id + ":" + firstName + ":" + lastName;
    }

}
