package hu.schonhertz.training.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import hu.schonhertz.training.dao.BlogDao;
import hu.schonhertz.training.dao.impl.BlogDaoImpl;

/**
 * Servlet implementation class BlogPosterServlet
 */
public class BlogPosterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CREATOR = "creator";
	private static final String TITLE = "title";
	private static final String TEXT = "text";
	private static final String ERRORS = "errors";
	
	private BlogDao blogDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogPosterServlet() {
        super();
        blogDao = new BlogDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // Initializing
	  PrintWriter out = response.getWriter();
	  @SuppressWarnings("unchecked")
    Map<String, String> errors = (request.getAttribute(ERRORS) != null ? (HashMap<String, String>) request.getAttribute(ERRORS) : new HashMap<>());

    // title of the page
    out.println("<title>Nospring Post A New Blog</title>");
	  // import boostrap.min.css
    out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" >");
    
		// page header
    out.println("<div class='page-header col-md-10 col-md-offset-1'><h1>Post a new blog</h1></div>");
    
    // form
    out.println("<div class='col-md-10 col-md-offset-1'>");
    out.println("<form method='POST'>");
    // creator
    out.println("<div class='form-group'>");
    out.println("<label class='control-label' for='" + CREATOR + "'>Your name</label>");
		out.println("<input type='text' class='form-control' name='" + CREATOR + "' id='" + CREATOR + "' placeholder='Anonymous'>");
		out.println("</div>");
		// title
		out.println("<div class='form-group " + (errors.containsKey(TITLE) ? "has-error" : "") + "'>");
    out.println("<label class='control-label' for='" + TITLE + "'>Title of your blog</label>");
    out.println("<input type='text' class='form-control' name='" + TITLE + "' id='" + TITLE + "' placeholder='Title'>");
    if(errors.containsKey(TITLE)) {
      out.println("<span class='help-block'>" + errors.get(TITLE) + "</span>");
    }
    out.println("</div>");
    // text
    out.println("<div class='form-group " + (errors.containsKey(TEXT) ? "has-error" : "") + "'>");
    out.println("<label class='control-label' for='" + TEXT + "'>Text of your blog</label>");
    out.println("<textarea id='" + TEXT + "' name='" + TEXT + "' class='form-control' rows='6' placeholder='Text'></textarea>");
    if(errors.containsKey(TEXT)) {
      out.println("<span class='help-block'>" + errors.get(TEXT) + "</span>");
    }
    out.println("</div>");
    // button
    out.println("<button type='submit' class='btn btn-primary'>Post the comment</button>");
    out.println("<a class='btn btn-danger pull-right' role='button' href='" + request.getContextPath() + "'>Cancel</a>");
    out.println("</form>");
    out.println("</div>");
    
    // reset the errors
    errors = new HashMap<>();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Initializing
	  String creator = request.getParameter(CREATOR);
		String title = request.getParameter(TITLE);
		String text = request.getParameter(TEXT);
		Map<String, String> errors = new HashMap<>();
		
		// Validation of creator
		if (creator.equals("")) {
		  // if it is empty then name will be Anonymous
		  creator = null;
		} else {
		  creator = StringEscapeUtils.escapeHtml(creator);
		}
		
		// Validation of title
		if (title.equals("")) {
		  errors.put(TITLE, "Title sould not be empty");
		} else {
		  title = StringEscapeUtils.escapeHtml(title);
		}
		
		// Validation of text
		if (text.equals("")) {
		  errors.put(TEXT, "Text of blog should not be empty");
		} else {
		  text = StringEscapeUtils.escapeHtml(text);
		}
		
		// If there is an error then it will shown 
		if (!errors.isEmpty()) {
		  request.setAttribute(ERRORS, errors);
		  doGet(request, response);
		// If everything is OK then save and go to main page
		} else {
		  if (creator == null) {
		    blogDao.addBlog(title, text);
		  } else {
		    blogDao.addBlogWithCreator(creator, title, text);
		  }
		  response.sendRedirect(request.getContextPath());
		}
		
	}

}
