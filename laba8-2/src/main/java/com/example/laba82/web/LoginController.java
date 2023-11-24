package com.example.laba82.web;

import com.example.laba82.dao.LoginDao;
import com.example.laba82.model.LoginBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
	private static final Logger logger = LogManager.getLogger(LoginController.class);

	public void init() {
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		try {
			if (loginDao.validate(loginBean)) {
				logger.info("User '{}' logged in successfully", username);
				response.sendRedirect("list");
			} else {
				HttpSession session = request.getSession();
				// session.setAttribute("user", username);
				// response.sendRedirect("login.jsp");
				logger.warn("Login failed for user '{}'", username);
			}
		} catch (ClassNotFoundException e) {
			logger.error("Error during user authentication: {}", e.getMessage());
			e.printStackTrace();
		}
	}
}