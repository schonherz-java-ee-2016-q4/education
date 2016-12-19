package hu.schonhertz.training.databaseconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBCConnector {
  
  public static Connection getConnection() throws SQLException {
    Connection conn = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blog", "postgres", "pass");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return conn;
  }

}
