package hu.schonherz.training.jdbc.dao;

import hu.schonherz.training.jdbc.records.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ikant on 2016. 07. 23..
 */
@Repository
public class JDBCTempateStudentDAOImpl implements StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> getAllStudents() {
        return null;
    }

    public Student getStudent(String name) {
        return null;
    }

    public void addStudent(Student student) {

    }

    public void updateStudent(Student student) {

    }

    public void deleteStudent(Student student) {

    }
}
