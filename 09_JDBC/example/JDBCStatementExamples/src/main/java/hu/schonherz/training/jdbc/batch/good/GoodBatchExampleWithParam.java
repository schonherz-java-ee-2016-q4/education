package hu.schonherz.training.jdbc.batch.good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hu.schonherz.training.jdbc.ConnectionHandler;
import hu.schonherz.training.jdbc.records.Student;

public class GoodBatchExampleWithParam {

	public static void main(String[] argv) {

		String sql = "INSERT INTO public.\"Student\" (firstname, lastname) VALUES (?, ?)";
		List<Student> students = new ArrayList<>();
		students.add(new Student(0, "Janos", "Pelsoczi"));
		students.add(new Student(0, "Laszlo", "Farkas"));
		students.add(new Student(0, "Attila", "Zelei"));

		try (Connection connection = ConnectionHandler.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			connection.setAutoCommit(false);
			for (Student student : students) {
				statement.setString(1, student.getFirstName());
				statement.setString(2, student.getLastName());
				statement.addBatch();
				System.out.println("Student: " + student.getFirstName() + " added to batch.");
			}
			statement.executeBatch();
			System.out.println("Batch executed...");
			connection.commit();
			System.out.println("Batch commited...");			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
