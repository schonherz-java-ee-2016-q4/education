package hu.schonhertz.training.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hu.schonhertz.training.pojo.Blog;


public class BlogMapper implements RowMapper<Blog> {

  @Override
  public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
    Blog blog = new Blog();
    blog.setId(rs.getInt("id"));
    blog.setCreator(rs.getString("creator"));
    blog.setTitle(rs.getString("title"));
    blog.setText(rs.getString("text"));
    return blog;
  }

}
