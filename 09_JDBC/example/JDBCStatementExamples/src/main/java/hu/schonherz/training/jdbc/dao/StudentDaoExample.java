package hu.schonherz.training.jdbc.dao;

import java.util.List;

import hu.schonherz.training.jdbc.records.Student;

public class StudentDaoExample {

	public static void main(String[] argv) {
		StudentDao dao = new StudentDaoImpl();
		List<Student> students = dao.getAllStudents();
		System.out.println("--- Students ---");
		for (Student s : students) {
			System.out.println(s);
		}
		String firstName = "Janos";
		Student student = dao.getStudent(firstName);
		System.out.println("--- Student " + firstName + "---");
		System.out.println(student);

		student = new Student(0, "Zoltán", "Kiss");
		dao.addStudent(student);
		System.out.println("--- Add Student " + student.getFirstName() + "---");
		System.out.println(student);

		student.setLastName("Nagy");
		dao.updateStudent(student);
		System.out.println("--- Update Student " + student.getFirstName() + "---");
		System.out.println(student);

		students = dao.getAllStudents();
		System.out.println("--- All Students Before Delete ---");
		for (Student s : students) {
			System.out.println(s);
		}

		dao.deleteStudent(student);
		System.out.println("--- Delete Student " + student.getFirstName() + "---");
		System.out.println(student);

		students = dao.getAllStudents();
		System.out.println("--- All Students After Delete ---");
		for (Student s : students) {
			System.out.println(s);
		}
	}

}
