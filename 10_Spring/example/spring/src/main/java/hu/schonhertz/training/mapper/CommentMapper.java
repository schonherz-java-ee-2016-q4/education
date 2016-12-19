package hu.schonhertz.training.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hu.schonhertz.training.pojo.Comment;


public class CommentMapper implements RowMapper<Comment> {

  @Override
  public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
    Comment comment = new Comment();
    comment.setId(rs.getInt("id"));
    comment.setBlogId(rs.getInt("blog_id"));
    comment.setComment(rs.getString("comment"));
    return comment;
  }
  
}
