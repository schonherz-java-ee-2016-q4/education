package hu.schonhertz.training.blog.web.servlet;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import hu.schonhertz.training.blog.service.UserService;
import hu.schonhertz.training.blog.vo.UserVo;

/**
 * This servlet handles the message box.
 */
@WebServlet(name = "/RegistrationServlet", urlPatterns = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

	@Autowired
	private UserService userService;

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RegistrationServlet.class);

	@PostConstruct
	public void postConstruct() {
		System.out.println("postconstruct has run.");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");

			if (!password.equals(password2)) {
				request.setAttribute("error", "Password not match!");
			} else if (userService.findUserByName(username) != null) {
				request.setAttribute("error", "Username exits!");
			} else {

				UserVo userVO = new UserVo();

				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String encPassword = bCryptPasswordEncoder.encode(password);

				userVO.setPassword(encPassword);
				userVO.setUserName(username);

				userService.registrationUser(userVO);

				request.setAttribute("msg", "Success");

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		request.getRequestDispatcher("/pages/registration.jsp").forward(request, response);

	}
}
