package hu.schonhertz.training.dao;

import java.util.List;

import hu.schonhertz.training.bean.Comment;

public interface CommentDao {
  
  /**
   * Return all comments which belong to specified blog
   * @param blogId id of the blog
   * @return list of comments
   */
  public List<Comment> getAllCommentsByBlogId(int blogId);
  
  /**
   * Add a new comment under the specified blog
   * @param blogId id of the blog
   * @param comment text of the new comment
   */
  public void addComment(int blogId, String comment);

}
