package hu.schonherz.training.jdbc.batch.bad;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import hu.schonherz.training.jdbc.ConnectionHandler;

public class BadBatchExample {

	public static void main(String[] argv) {

		String[] queries = { 
				"INSERT INTO public.\"Student\" (firstname, lastname) VALUES ('Janos', 'Pelsoczi')",
				"INSERT INTO public.\"Student\" (firstname, lastname) VALUES ('Laszlo', 'Farkas')",
				"INSERT INTO public.\"Student\" (firstname, lastname) VALUES ('Attila', 'Zelei')", };

		try (Connection connection = ConnectionHandler.getConnection();
				Statement statement = connection.createStatement();) {
			for (String query : queries) {
				statement.execute(query);
				System.out.println("Query string executed.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
