package hu.schonherz.training.jdbc.batch.good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import hu.schonherz.training.jdbc.ConnectionHandler;
import hu.schonherz.training.jdbc.records.Student;

public class GoodBatchExampleWithBatchSize {

	public static void main(String[] argv) {

		String sql = "INSERT INTO public.\"Student\" (firstname, lastname) VALUES (?, ?)";
		List<Student> students = new ArrayList<>();
		students.add(new Student(0, "Janos", "Pelsoczi"));
		students.add(new Student(0, "Laszlo", "Farkas"));
		students.add(new Student(0, "Attila", "Zelei"));
		students.add(new Student(0, "István", "Juhász"));

		final int batchSize = 2;
		int count = 0;
		//Stack<Savepoint> savePoints = new Stack<>();

		try (Connection connection = ConnectionHandler.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			connection.setAutoCommit(false);
			for (Student student : students) {
				statement.setString(1, student.getFirstName());
				statement.setString(2, student.getLastName());
				statement.addBatch();
				System.out.println("Student: " + student.getFirstName() + " added to batch.");
				if (++count % batchSize == 0) {
					System.out.println("Count size: " + count);
					System.out.println("Count size reaches the batch size...");
					int[] executedBatchSize = statement.executeBatch();
					System.out.println("Batch executed... Batch Size: " + executedBatchSize.length);
					//savePoints.push(connection.setSavepoint());
					//System.out.println("Save point added...");					
				}
			}
			int[] remainingRows = statement.executeBatch();
			System.out.println("Batch executed for remaining rows... Batch Size: " + remainingRows.length);
			//savePoints.push(connection.setSavepoint());			
			//System.out.println("Save point added...");
			//savePoints.pop();
			//connection.rollback(savePoints.pop());
			connection.commit();
			System.out.println("Batch commited...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
