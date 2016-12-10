package hu.schonherz.training.jdbc.batch.good;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import hu.schonherz.training.jdbc.ConnectionHandler;

public class GoodBatchExample {

	public static void main(String[] argv) {

		String[] queries = { "INSERT INTO public.\"Student\" (firstname, lastname) VALUES ('Janos', 'Pelsoczi')",
				"INSERT INTO public.\"Student\" (firstname, lastname) VALUES ('Laszlo', 'Farkas')",
				"INSERT INTO public.\"Student\" (firstname, lastname) VALUES ('Attila', 'Zelei')", };

		try (Connection connection = ConnectionHandler.getConnection();
				Statement statement = connection.createStatement();) {
			connection.setAutoCommit(false);
			for (String query : queries) {
				statement.addBatch(query);
				System.out.println("Query string added.");
			}
			statement.executeBatch();
			System.out.println("Batch executed.");
			connection.commit();
			System.out.println("Batch commited.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
