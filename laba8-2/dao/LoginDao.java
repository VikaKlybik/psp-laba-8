package com.example.laba82.dao;

import com.example.laba82.model.LoginBean;
import com.example.laba82.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

	private static final Logger logger = LogManager.getLogger(LoginDao.class);

	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
		boolean status = false;
		logger.info("Validate user login input");
		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
						.prepareStatement("select * from users where username = ? and password = ? ")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			logger.debug("Validate user login input stop with state '"+status+"'");

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
			logger.debug("Error while validation");
		}
		return status;
	}
}