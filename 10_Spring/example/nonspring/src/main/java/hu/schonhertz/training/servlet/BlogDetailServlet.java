package hu.schonhertz.training.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import hu.schonhertz.training.bean.Blog;
import hu.schonhertz.training.bean.Comment;
import hu.schonhertz.training.dao.BlogDao;
import hu.schonhertz.training.dao.CommentDao;
import hu.schonhertz.training.dao.impl.BlogDaoImpl;
import hu.schonhertz.training.dao.impl.CommentDaoImpl;

/**
 * Servlet implementation class BlogDetailServlet
 */
public class BlogDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NEWCOMMENT = "comment";
	private static final String ID = "id";
	
	private int id;
	private BlogDao blogDao;
	private Blog blog;
	private CommentDao commentDao;
	private List<Comment> comments;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogDetailServlet() {
        super();
        blogDao = new BlogDaoImpl();
        commentDao = new CommentDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // Initializing 
	  id = Integer.parseInt(request.getParameter(ID));
	  blog = blogDao.getBlog(id);
	  comments = commentDao.getAllCommentsByBlogId(id);
	  PrintWriter out = response.getWriter();

    // title of the page
    out.println("<title>Nospring Blog Details</title>");
	  // import boostrap.min.css
    out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" >");
    
    // page header
    out.println("<div class='page-header col-md-10 col-md-offset-1'>");
    out.println("<h1><span style='cursor: pointer;' onclick='window.location =\"" + request.getContextPath() + "\"'>Blogs - </span>");
    out.println(blog.getTitle() + "<small> - by " + blog.getCreator() + "</small>");
    out.println("</h1>");
    out.println("</div>");
    
    // text of blog
    out.println("<div class='col-md-10 col-md-offset-1'><pre>" + blog.getText() + "</pre></div>");
    
    // comments
    out.println("<div class='col-md-9 col-md-offset-1'><h3>Comments</h3></div>");
    if (comments.size() > 0) {
      for (Comment comment : comments) {
        out.println("<div class='col-md-9 col-md-offset-1'><pre>" + comment.getComment() + "</pre></div>");
      }
    } else {
      out.println("<div class='col-md-9 col-md-offset-1'><pre>There is no comment yet</pre></div>");
    }
    
    // post new comment
    out.println("<div class='col-md-9 col-md-offset-1'><h3>Post a comment</h3>");
    out.println("<form action='blog?id=" + id + "' method='POST'>");
    //out.println("<input type='hidden' name='" + ID + "' value='" + blog.getId() + "'>");
    out.println("<div class='form-group'>");
    out.println("<textarea name='" + NEWCOMMENT + "' class='form-control' rows='3' placeholder='Comment'></textarea>");
    out.println("</div>");
    out.println("<button type='submit' class='btn btn-primary'>Submit your comment</button>");
    out.println("</form>");
    out.println("</div>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // Initializing
	  String comment = request.getParameter(NEWCOMMENT);
	  int blogId = Integer.parseInt(request.getParameter(ID));
	  
	  // save the new comment if it is not empty
	  if (!comment.equals("")) {
	    commentDao.addComment(blogId, StringEscapeUtils.escapeHtml(comment));
	  }
	  
	  // do get method
	  this.doGet(request, response);
	}

}
