package hu.schonherz.training.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {
    public static Connection getConnection() throws SQLException {
        return
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres", "pass");
    }
}
