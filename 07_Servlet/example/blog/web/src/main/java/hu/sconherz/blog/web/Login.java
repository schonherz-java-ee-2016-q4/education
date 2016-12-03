package hu.sconherz.blog.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.schonherz.blog.service.UserServiceImpl;
import hu.schonherz.blog.service.api.User;
import hu.schonherz.blog.service.api.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginForm loginForm = new LoginForm(request);

		String username = loginForm.getUsername();
		UserService userService = new UserServiceImpl();
		User user = null;
		try {
			user = userService.findUserByName(username);
		} catch (Exception e) {
		}

		if (user != null) {
			String password = loginForm.getPassword();
			if (user.getLogin().getPassword().equals(password)) {
				response.sendRedirect("secured/secured.jsp");
			} else {
				request.setAttribute("error", "bajvan");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "bajvan2");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
