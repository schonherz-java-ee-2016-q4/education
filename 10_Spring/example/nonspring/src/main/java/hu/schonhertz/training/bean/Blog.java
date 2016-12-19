package hu.schonhertz.training.bean;


public class Blog {
  
  private int id;
  private String creator;
  private String title;
  private String text;
  
  public Blog() {
    super();
  }
  
  public final int getId() {
    return id;
  }
  
  public final void setId(int id) {
    this.id = id;
  }
  
  public final String getCreator() {
    return creator;
  }
  
  public final void setCreator(String creator) {
    this.creator = creator;
  }
  
  public final String getTitle() {
    return title;
  }
  
  public final void setTitle(String title) {
    this.title = title;
  }
  
  public final String getText() {
    return text;
  }
  
  public final void setText(String text) {
    this.text = text;
  }
  

}
