package hu.schonhertz.training.dao;

import java.util.List;

import hu.schonhertz.training.bean.Blog;

public interface BlogDao {
  
  /**
   * Get all blog
   * @return list of blogs
   */
  public List<Blog> getAllBlog();
  
  /**
   * Get a specified blog
   * @param id id of the blog
   * @return the blog
   */
  public Blog getBlog(int id);
  
  /**
   * Add a new blog
   * by default it add a new blog with "Anonymous" creator
   * @see BlogDao#addBlogWithCreator(String, String, String)
   * @param title title of the blog
   * @param text text of the blog
   */
  public default void addBlog(String title, String text) {
    this.addBlogWithCreator("Anonymous", title, text);
  }
  
  /**
   * Add a new blog with creator
   * @param creator name of creator
   * @param title title of the blog
   * @param text text of the blog
   */
  public void addBlogWithCreator(String creator, String title, String text);

}
