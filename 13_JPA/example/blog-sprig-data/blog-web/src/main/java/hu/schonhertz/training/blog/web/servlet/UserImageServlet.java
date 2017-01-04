package hu.schonhertz.training.blog.web.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import hu.schonhertz.training.blog.service.UserService;
import hu.schonhertz.training.blog.vo.UserVo;

@WebServlet("/userImage")
public class UserImageServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(UserImageServlet.class.getName());

	private static final long serialVersionUID = 1795959081410371020L;

	@Autowired
	UserService userService;

	@PostConstruct
	public void postConstruct() {
		System.out.println("postconstruct has run.");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		String parameter = request.getParameter("username");

		if (!StringUtils.isEmpty(parameter)) {

			UserVo userVo = null;
			try {
				userVo = userService.findUserByName(parameter);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			byte[] image = userVo.getImage();
			if (image != null) {
				ByteArrayInputStream in = new ByteArrayInputStream(image);
				IOUtils.copy(in, resp.getOutputStream());
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(resp.getOutputStream());
				resp.setContentType("image/jpeg");
				resp.addHeader("Cache-Control", "public, max-age=90000");
				resp.setStatus(HttpServletResponse.SC_OK);
			} else {

				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}

		}
	}
}
