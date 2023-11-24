package com.example.laba82.dao;

import com.example.laba82.model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDao {

	void insertTodo(Todo todo) throws SQLException;

	Todo selectTodo(long todoId);

	List<Todo> selectAllTodos(String username);

	boolean deleteTodo(int id) throws SQLException;

	boolean updateTodo(Todo todo) throws SQLException;

}