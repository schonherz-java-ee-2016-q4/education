package hu.schonhertz.training.blog.web.servlet;

import java.io.IOException;
import java.util.Set;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.gson.Gson;

import hu.schonhertz.training.blog.service.BlogService;
import hu.schonhertz.training.blog.service.CommentService;
import hu.schonhertz.training.blog.service.UserService;
import hu.schonhertz.training.blog.vo.BlogVo;
import hu.schonhertz.training.blog.vo.CommentVo;
import hu.schonhertz.training.blog.vo.UserVo;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(BlogServlet.class.getName());

	private static final long serialVersionUID = -3759352952465316112L;

	@Autowired
	CommentService commentService;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		CommentVo commentVo = new CommentVo();
		commentVo.setComment(request.getParameter("comment"));

		Long blogId = Long.valueOf(request.getParameter("blogId"));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		UserVo curerntUser = null;
		try {
			curerntUser = userService.findUserByName(name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		commentVo.setCreator(curerntUser);
		commentService.addCommnet(commentVo, blogId);

		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.getWriter().write("OK");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = request.getParameter("blogId");
		if (!StringUtils.isEmpty(parameter)) {

			Long blogId = Long.valueOf(parameter);

			BlogVo blogVo = blogService.getBlogById(blogId);
			Set<CommentVo> commentVos = blogVo.getComments();

			resp.setContentType("application/json");
			resp.setStatus(HttpServletResponse.SC_OK);

			Gson gson = new Gson();
			gson.toJson(commentVos, resp.getWriter());
		}
		resp.setContentType("application/json");
		resp.setStatus(HttpServletResponse.SC_OK);
	}
}
