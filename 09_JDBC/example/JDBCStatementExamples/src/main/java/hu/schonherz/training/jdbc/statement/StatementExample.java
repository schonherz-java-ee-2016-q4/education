package hu.schonherz.training.jdbc.statement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.schonherz.training.jdbc.ConnectionHandler;
import hu.schonherz.training.jdbc.records.Student;

public class StatementExample {

	public static void main(String[] argv) {

		selectRecordsFromTable();
		selectRecordsFromTableWithParameter();
		selectRecordsFromTableWithSP();
		selectRecordsFromTableWithParameterizedSP();

	}

	private static void selectRecordsFromTable() {

		String sql = "SELECT id, firstname, lastname FROM public.\"Student\"";
		List<Student> students = new ArrayList<>();
		try (Connection con = ConnectionHandler.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rs = stmt.executeQuery(sql);) {
				while (rs.next()) {
					students.add(new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("--- Statement Result ---");
		for (Student s : students) {
			System.out.println(
					"ID: " + s.getId() + " First Name: " + s.getFirstName() + " Last Name: " + s.getLastName());
		}

	}

	private static void selectRecordsFromTableWithParameter() {

		String sql = "SELECT id, firstname, lastname FROM public.\"Student\" WHERE firstname = ?";
		List<Student> students = new ArrayList<>();
		try (Connection con = ConnectionHandler.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, "Janos");
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					students.add(new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("--- PreparedStatement Result ---");
		for (Student s : students) {
			System.out.println(
					"ID: " + s.getId() + " First Name: " + s.getFirstName() + " Last Name: " + s.getLastName());
		}

	}

	private static void selectRecordsFromTableWithSP() {

		String sql = "{call \"getStudents\"()}";
		List<Student> students = new ArrayList<>();
		try (Connection con = ConnectionHandler.getConnection(); CallableStatement stmt = con.prepareCall(sql)) {
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					students.add(new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("--- CallableStatement Result ---");
		for (Student s : students) {
			System.out.println(
					"ID: " + s.getId() + " First Name: " + s.getFirstName() + " Last Name: " + s.getLastName());
		}

	}

	private static void selectRecordsFromTableWithParameterizedSP() {

		String sql = "{call \"getStudentsWithParam\"(?)}";
		List<Student> students = new ArrayList<>();
		try (Connection con = ConnectionHandler.getConnection(); CallableStatement stmt = con.prepareCall(sql)) {
			stmt.setInt(1, 2);
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					students.add(new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("--- CallableStatement With Parameter Result ---");
		for (Student s : students) {
			System.out.println(
					"ID: " + s.getId() + " First Name: " + s.getFirstName() + " Last Name: " + s.getLastName());
		}

	}

}
