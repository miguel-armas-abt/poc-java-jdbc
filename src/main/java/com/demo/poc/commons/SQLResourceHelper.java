package com.demo.poc.commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SQLResourceHelper {

  public static void rollback(Connection connection) {
    try {
      if (connection != null)
        connection.rollback();
    } catch (SQLException exception) {
      throw new RuntimeException("Error to rollback: " + exception.getMessage());
    }
  }

  public static void closeResources(PreparedStatement statement, ResultSet result) {
    closeResource(statement);
    closeResource(result);
  }

  public static void closeResource(Connection connection) {
    try {
      if (connection != null)
        connection.close();
    } catch (SQLException exception) {
      throw new RuntimeException("Error to close connection resource: " + exception.getMessage());
    }
  }

  public static void closeResource(PreparedStatement statement) {
    try {
      if (statement != null)
        statement.close();
    } catch (SQLException exception) {
      throw new RuntimeException("Error to close prepared statement resource: " + exception.getMessage());
    }
  }

  public static void closeResource(ResultSet result) {
    try {
      if (result != null)
        result.close();
    } catch (SQLException exception) {
      throw new RuntimeException("Error to close result set resource: " + exception.getMessage());
    }
  }
}
