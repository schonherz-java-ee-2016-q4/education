package hu.schonherz.training.jdbc.dao;

import java.util.List;

import hu.schonherz.training.jdbc.records.Student;

public interface StudentDao {

	public List<Student> getAllStudents();

	public Student getStudent(String name);
	
	public void addStudent(Student student);

	public void updateStudent(Student student);

	public void deleteStudent(Student student);
}
