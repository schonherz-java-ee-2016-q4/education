package hu.schonhertz.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.schonhertz.training.bean.Blog;
import hu.schonhertz.training.dao.BlogDao;
import hu.schonhertz.training.databaseconnector.PostgreSQLJDBCConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDaoImpl implements BlogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Blog> getAllBlog() {
        return jdbcTemplate.query("SELECT id, creator, title, text FROM public.\"Blog\";",
                ((resultSet, i) -> {
                    Blog blog = new Blog();
                    blog.setId(resultSet.getInt("id"));
                    blog.setCreator(resultSet.getString("creator"));
                    blog.setTitle(resultSet.getString("title"));
                    blog.setText(resultSet.getString("text"));
                    return blog;
                }));
    }

    @Override
    public Blog getBlog(int id) {
        Blog blog = null;
        try (Connection conn = PostgreSQLJDBCConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, creator, title, text FROM public.\"Blog\" WHERE id = ?;");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setCreator(rs.getString("creator"));
                blog.setTitle(rs.getString("title"));
                blog.setText(rs.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blog;
    }

    @Override
    public void addBlogWithCreator(String creator, String title, String text) {
        try (Connection conn = PostgreSQLJDBCConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO public.\"Blog\"(creator, title, text) VALUES (?, ?, ?);");
            stmt.setString(1, creator);
            stmt.setString(2, title);
            stmt.setString(3, text);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
