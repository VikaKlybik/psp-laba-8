package com.example.laba82.web;

import com.example.laba82.dao.TodoDao;
import com.example.laba82.dao.TodoDaoImpl;
import com.example.laba82.model.Todo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDao todoDAO;
	private static final Logger logger = LogManager.getLogger(TodoController.class);

	public void init() {
		todoDAO = new TodoDaoImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertTodo(request, response);
					break;
				case "/delete":
					deleteTodo(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateTodo(request, response);
					break;
				case "/list":
					listTodo(request, response);
					break;
				default:
					logger.info("Redirecting to login page");
					RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
					dispatcher.forward(request, response);
					break;
			}
		} catch (SQLException ex) {
			logger.error("Error processing request: {}", ex.getMessage());
			throw new ServletException(ex);
		}
	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		logger.info("Displaying list of todos");
		List<Todo> listTodo = todoDAO.selectAllTodos();
		request.setAttribute("listTodo", listTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Showing new todo form");
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		logger.info("Showing edit form for todo with ID {}", id);
		Todo existingTodo = todoDAO.selectTodo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);
	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		logger.info("Inserting new todo");
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Todo newTodo = new Todo(title, username, description, LocalDate.now(), isDone);
		todoDAO.insertTodo(newTodo);
		response.sendRedirect("list");
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		logger.info("Updating todo");
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);
		todoDAO.updateTodo(updateTodo);
		response.sendRedirect("list");
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		logger.info("Deleting todo");
		int id = Integer.parseInt(request.getParameter("id"));
		todoDAO.deleteTodo(id);
		response.sendRedirect("list");
	}
}