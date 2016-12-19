package hu.schonhertz.training.jdbctemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hu.schonhertz.training.dao.CommentDao;
import hu.schonhertz.training.mapper.CommentMapper;
import hu.schonhertz.training.pojo.Comment;

// @Repository the component-scan realize that it is a "repository"
@Repository
public class CommentJDBCTemplate implements CommentDao {
  
  // this dependency will automatically "wired" injected
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Comment> getAllCommentByBlogId(int blogId) {
    String sql = "SELECT id, blog_id, comment FROM public.\"Comment\" WHERE blog_id = ?;";
    List<Comment> comments = jdbcTemplate.query(sql, new CommentMapper(), blogId);
    return comments;
  }

  @Override
  public void createComment(int blogId, String comment) {
    String sql = "INSERT INTO public.\"Comment\"(blog_id, comment) VALUES (?, ?);";
    jdbcTemplate.update(sql, blogId, comment);
  }

}
