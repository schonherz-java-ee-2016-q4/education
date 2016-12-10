package hu.schonherz.training.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.schonherz.training.jdbc.ConnectionHandler;
import hu.schonherz.training.jdbc.records.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao {

	private Connection con;

	public StudentDaoImpl() {
		try {
			con = ConnectionHandler.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> getAllStudents() {
		String sql = "SELECT id, firstname, lastname FROM public.\"Student\"";
		List<Student> students = new ArrayList<>();
		try (Statement stmt = con.createStatement()) {
			try (ResultSet rs = stmt.executeQuery(sql);) {
				while (rs.next()) {
					students.add(new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public Student getStudent(String name) {
		String sql = "SELECT id, firstname, lastname FROM public.\"Student\" WHERE firstname = ?";
		Student student = null;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, name);
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					student = new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public void updateStudent(Student student) {
		String sql = "UPDATE public.\"Student\" SET firstname = ?, lastname = ? WHERE id = ? ;";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setInt(3, student.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteStudent(Student student) {
		String sql = "DELETE FROM public.\"Student\" WHERE firstname = ? ;";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, student.getFirstName());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addStudent(Student student) {
		String sql = "INSERT INTO public.\"Student\" (firstname, lastname) VALUES (?,?) ;";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int userId = rs.getInt("id");
				student.setId(userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
