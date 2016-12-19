package hu.schonhertz.training.pojo;


public class Comment {
  
  private int id;
  private int blogId;
  private String comment;
  
  public Comment() {
    super();
  }
  
  public final int getId() {
    return id;
  }
  
  public final void setId(int id) {
    this.id = id;
  }
  
  public final int getBlogId() {
    return blogId;
  }
  
  public final void setBlogId(int blogId) {
    this.blogId = blogId;
  }

  public final String getComment() {
    return comment;
  }
  
  public final void setComment(String comment) {
    this.comment = comment;
  }

}
