package com.demo.poc.dao;

import com.demo.poc.commons.MySQLConnection;
import com.demo.poc.dto.EmployeeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Patrón de diseño DAO. Útil para realizar consultas a base de datos haciendo uso de instrucciones SQL nativas.
 *
 * connection.setAutoCommit(false): Las transacciones en BD no se confirmarán automáticamente. A continuación, debes confirmar o deshacer explícitamente mediante commit o rollback, respectivamente
 * connection.commit(): Confirmamos la transacción al finalizar la operación exitosamente
 * connection.rollback(): Deshacemos la transacción en caso de excepción
 */
public class EmployeeDaoImpl implements EmployeeDao {

  private Connection connection = null;
  private PreparedStatement statement = null;
  private ResultSet result;

  @Override
  public List<EmployeeDTO> findAll() {
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);
      statement = connection.prepareStatement("SELECT code, name, contract_date, department_code FROM employees;");
      result = statement.executeQuery();

      List<EmployeeDTO> employeeList = new ArrayList<>();
      while (result.next()) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setCode(result.getInt("code"));
        employeeDto.setName(result.getString("name"));
        employeeDto.setContractDate(result.getDate("contract_date"));
        employeeDto.setDepartmentCode(result.getInt("department_code"));

        employeeList.add(employeeDto);
      }
      connection.commit();
      return employeeList;

    } catch (Exception exception) {
      rollback();
      throw new RuntimeException("error to find all employees: " + exception.getMessage());
    } finally {
      closeResources();
    }
  }

  @Override
  public EmployeeDTO findByCode(int code) {
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);
      statement = connection.prepareStatement("SELECT code, name, contract_date, department_code FROM employees WHERE code = ?");
      statement.setInt(1, code);
      result = statement.executeQuery();

      EmployeeDTO employee = new EmployeeDTO();;
      if (result.next()) {
        employee.setCode(result.getInt("code"));
        employee.setName(result.getString("name"));
        employee.setContractDate(result.getDate("contract_date"));
        employee.setDepartmentCode(result.getInt("department_code"));
      }
      connection.commit();
      return employee;

    } catch (Exception exception) {
      rollback();
      throw new RuntimeException("error to find employee by code: " + exception.getMessage());
    } finally {
      closeResources();
    }
  }

  @Override
  public void save(EmployeeDTO employee) {
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);
      statement = connection.prepareStatement("INSERT INTO employees (name, document_identification, contract_date, contract_type, department_code) VALUES (?, ?, ?, ?, ?);");
      statement.setString(1, employee.getName());
      statement.setInt(2, employee.getDocumentIdentification());
      statement.setDate(3, employee.getContractDate());
      statement.setString(4, employee.getContractType());
      statement.setInt(5, employee.getDepartmentCode());

      int insertedRows = statement.executeUpdate();
      if (insertedRows == 1) {
        connection.commit();
      } else {
        throw new RuntimeException("error to save employee");
      }
    } catch (Exception exception) {
      rollback();
    } finally {
      closeResources();
    }
  }

  @Override
  public void deleteByCode(int code) {
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);
      statement = connection.prepareStatement("DELETE FROM employees WHERE code = ?");
      statement.setInt(1, code);

      int deletedRows = statement.executeUpdate();
      if (deletedRows == 1) {
        connection.commit();
      } else {
        throw new RuntimeException("error to delete employee");
      }
    } catch (Exception exception) {
      rollback();
    } finally {
      closeResources();
    }
  }

  private void rollback() {
    try {
      if (connection != null) {
        connection.rollback();
      }
    } catch (Exception exception) {
      throw new RuntimeException("error to rollback: " + exception.getMessage());
    }
  }

  private void closeResources() {
    try {
      if (statement != null) {
        statement.close();
      }
      if (result != null) {
        result.close();
      }
    } catch (Exception exception) {
      throw new RuntimeException("error to close resources: " + exception.getMessage());
    }
  }
}
