package hu.schonhertz.training.blog.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import hu.schonhertz.training.blog.service.BlogService;
import hu.schonhertz.training.blog.service.UserService;
import hu.schonhertz.training.blog.vo.BlogVo;
import hu.schonhertz.training.blog.vo.UserVo;

@WebServlet("/createBlog")
@MultipartConfig
public class CreateBlogServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(CreateBlogServlet.class.getName());

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BlogVo blogVo = new BlogVo();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		UserVo curerntUser = null;
		try {
			curerntUser = userService.findUserByName(name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		blogVo.setCreator(curerntUser);
		blogVo.setTitle(request.getParameter("title"));
		blogVo.setText(request.getParameter("text"));

		Part filePart = request.getPart("file");

		InputStream in = filePart.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(in);
		IOUtils.closeQuietly(out);

		blogVo.setImage(out.toByteArray());
		blogService.createBlog(blogVo);

		request.setAttribute("msg", "Success");

		request.getRequestDispatcher("/pages/secured/createBlog.jsp").forward(request, response);

	}

}
