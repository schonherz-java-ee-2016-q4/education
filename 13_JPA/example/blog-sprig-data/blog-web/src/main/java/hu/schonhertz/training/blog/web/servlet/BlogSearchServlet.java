package hu.schonhertz.training.blog.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.gson.Gson;

import hu.schonhertz.training.blog.service.BlogService;
import hu.schonhertz.training.blog.service.UserService;
import hu.schonhertz.training.blog.vo.BlogSearchResult;
import hu.schonhertz.training.blog.vo.BlogVo;

@WebServlet("/blogSearch")
public class BlogSearchServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(BlogSearchServlet.class.getName());

	private static final long serialVersionUID = 1795959081410371020L;

	@Autowired
	BlogService blogService;

	@Autowired
	UserService userService;

	@PostConstruct
	public void postConstruct() {
		System.out.println("postconstruct has run.");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		String title = request.getParameter("title");
		String page = request.getParameter("page");
		String size = request.getParameter("size");

		if (!StringUtils.isEmpty(title)) {
			Integer sizeInt = Integer.valueOf(size);

			Integer pageValue = Integer.valueOf(page);
			BlogSearchResult result = blogService.searchBlog(title, pageValue, sizeInt);
			List<BlogVo> blogVos = result.getBlogVos();
			Long blogSize = result.getTotalElement();

			request.getSession().setAttribute("pageSize", sizeInt);
			request.getSession().setAttribute("blogSize", blogSize);
			request.getSession().setAttribute("currentPage", pageValue);

			resp.setContentType("application/json");
			resp.setStatus(HttpServletResponse.SC_OK);

			Gson gson = new Gson();
			gson.toJson(blogVos, resp.getWriter());
		}
		resp.setStatus(HttpServletResponse.SC_OK);
	}
}
