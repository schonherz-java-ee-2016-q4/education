package hu.schonhertz.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.schonhertz.training.bean.Comment;
import hu.schonhertz.training.dao.CommentDao;
import hu.schonhertz.training.databaseconnector.PostgreSQLJDBCConnector;


public class CommentDaoImpl implements CommentDao {

  @Override
  public List<Comment> getAllCommentsByBlogId(int blogId) {
    List<Comment> comments = new ArrayList<Comment>();
    try (Connection conn = PostgreSQLJDBCConnector.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT id, blog_id, comment FROM public.\"Comment\" WHERE blog_id = " + blogId + ";");
      while (rs.next()){
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setBlogId(rs.getInt("blog_id"));
        comment.setComment(rs.getString("comment"));
        comments.add(comment);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return comments;
  }

  @Override
  public void addComment(int blogId, String comment) {
    try (Connection conn = PostgreSQLJDBCConnector.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO public.\"Comment\"(blog_id, comment) VALUES (?, ?);");
      stmt.setInt(1, blogId);
      stmt.setString(2, comment);
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
