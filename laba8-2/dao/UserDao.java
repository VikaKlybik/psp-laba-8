package com.example.laba82.dao;

import com.example.laba82.model.User;
import com.example.laba82.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

	private static final Logger logger = LogManager.getLogger(UserDao.class);

	public int registerEmployee(User employee) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO users"
				+ "  (first_name, last_name, username, password) VALUES "
				+ " (?, ?, ?, ?);";

		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
			 // Step 2: Create a statement using connection object
			 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUsername());
			preparedStatement.setString(4, employee.getPassword());

			logger.debug("Executing SQL query: {}", preparedStatement);

			// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();

			logger.info("{} user(s) registered successfully", result);

		} catch (SQLException e) {
			// Process SQL exception
			JDBCUtils.printSQLException(e);
			logger.error("Error during user registration: {}", e.getMessage());
		}
		return result;
	}
}