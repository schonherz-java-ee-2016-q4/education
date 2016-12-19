package hu.schonhertz.training.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.schonhertz.training.bean.Blog;
import hu.schonhertz.training.dao.BlogDao;
import hu.schonhertz.training.dao.impl.BlogDaoImpl;

/**
 * Servlet implementation class CommentsServlet
 */
public class BlogsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private List<Blog> blogs;
    private BlogDao blogDao;

    /**
     * Read all blog from Database
     */
    public BlogsServlet() {
        blogDao = new BlogDaoImpl();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Initializing
        blogs = blogDao.getAllBlog();

        PrintWriter out = response.getWriter();

        // title of the page
        out.println("<title>Nospring Blog Index</title>");
        // import boostrap.min.css
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" >");

        // page header
        out.println("<div class='page-header col-md-10 col-md-offset-1'><h1>Blogs</h1></div>");

        // show blogs with creator
        for (Blog blog : blogs) {
            out.println("<div class='col-md-8 col-md-offset-2 bg-info' style='cursor: pointer; margin-bottom: 10px;' onclick='window.location = \"blog?id=" + blog.getId() + "\";'>");
            out.println("<h2>" + blog.getTitle() + "<small> - by " + blog.getCreator() + "</small>" + "</h2>");
            out.println("</div>");
        }

        out.println("<div class='col-md-8 col-md-offset-2' style='margin-top: 20px;'>");
        out.println("<a class='btn btn-primary' href='newblog' role='button'>Post a new blog</a>");
        out.println("</div>");
    }
}
