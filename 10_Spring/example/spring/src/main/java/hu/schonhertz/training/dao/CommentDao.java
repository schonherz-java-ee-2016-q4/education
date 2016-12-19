package hu.schonhertz.training.dao;

import java.util.List;

import hu.schonhertz.training.pojo.Comment;

public interface CommentDao {
  
  public List<Comment> getAllCommentByBlogId(int blogId);
  
  public void createComment(int blogId, String comment);

}
