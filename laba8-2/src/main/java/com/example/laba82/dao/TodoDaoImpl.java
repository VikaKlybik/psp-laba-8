package com.example.laba82.dao;

import com.example.laba82.model.Todo;
import com.example.laba82.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl implements TodoDao {

	private static final String INSERT_TODOS_SQL = "INSERT INTO todos"
			+ "  (title, username, description, target_date,  is_done) VALUES " + " (?, ?, ?, ?, ?);";

	private static final String SELECT_TODO_BY_ID = "select id,title,username,description,target_date,is_done from todos where id =?";
	private static final String SELECT_ALL_TODOS = "select * from todos";
	private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
	private static final String UPDATE_TODO = "update todos set title = ?, username= ?, description =?, target_date =?, is_done = ? where id = ?;";

	private static final Logger logger = LogManager.getLogger(TodoDaoImpl.class);

	public TodoDaoImpl() {
	}

	@Override
	public void insertTodo(Todo todo) throws SQLException {
		logger.info("Insert to-do");
		logger.debug(INSERT_TODOS_SQL);

		try (Connection connection = JDBCUtils.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getUsername());
			preparedStatement.setString(3, todo.getDescription());
			preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			preparedStatement.setBoolean(5, todo.getStatus());
			logger.debug(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
			logger.warn(exception.getMessage());
		}
	}

	@Override
	public Todo selectTodo(long todoId) {
		logger.info("Select to-do by ID");
		Todo todo = null;

		try (Connection connection = JDBCUtils.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID)) {
			preparedStatement.setLong(1, todoId);
			logger.debug(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				todo = new Todo(id, title, username, description, targetDate, isDone);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {
		logger.info("Select all to-dos");
		List<Todo> todos = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS)) {
			logger.debug(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				todos.add(new Todo(id, title, username, description, targetDate, isDone));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return todos;
	}

	@Override
	public boolean deleteTodo(int id) throws SQLException {
		logger.info("Delete to-do by ID");
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
			 PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID)) {
			statement.setInt(1, id);
			logger.debug(statement);

			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	@Override
	public boolean updateTodo(Todo todo) throws SQLException {
		logger.info("Update to-do");
		boolean rowUpdated;
		try (Connection connection = JDBCUtils.getConnection();
			 PreparedStatement statement = connection.prepareStatement(UPDATE_TODO)) {
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getUsername());
			statement.setString(3, todo.getDescription());
			statement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			statement.setBoolean(5, todo.getStatus());
			statement.setLong(6, todo.getId());
			logger.debug(statement);

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}