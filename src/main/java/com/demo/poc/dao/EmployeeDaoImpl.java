package com.demo.poc.dao;

import static com.demo.poc.commons.SQLResourceHelper.closeResource;
import static com.demo.poc.commons.SQLResourceHelper.closeResources;
import static com.demo.poc.commons.SQLResourceHelper.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.demo.poc.commons.MySQLConnection;
import com.demo.poc.entity.EmployeeEntity;
import com.demo.poc.mapper.EmployeeMapper;

/**
 * Patrón de diseño DAO. Accede a la base de datos utilizando instrucciones SQL nativas.
 *
 * connection.commit(): Establece que la transacción finalizó exitosamente.
 * connection.rollback(): Establece que la transacción falló y se debe reestablecer.
 * connection.setAutoCommit(false): Establece que las transacciones no se confirmarán automáticamente. Se deben confirmar o deshacer explícitamente mediante commit o rollback.
 */
public class EmployeeDaoImpl implements EmployeeDao {

  @Override
  public void save(EmployeeEntity employee) {
    Connection connection = null;
    PreparedStatement statement = null;
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

      if (insertedRows != 1)
        throw new RuntimeException("Error to save employee");

      connection.commit();

    } catch (SQLException exception) {
      rollback(connection);
    } finally {
      closeResource(statement);
    }
  }

  @Override
  public void deleteByCode(int code) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);
      statement = connection.prepareStatement("DELETE FROM employees WHERE code = ?");
      statement.setInt(1, code);

      int deletedRows = statement.executeUpdate();

      if (deletedRows != 1)
        throw new RuntimeException("Error to delete employee with code " + code);

      connection.commit();

    } catch (SQLException exception) {
      rollback(connection);
    } finally {
      closeResource(statement);
    }
  }

  @Override
  public List<EmployeeEntity> findAll() {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      statement = connection.prepareStatement("SELECT code, name, contract_date, department_code FROM employees;");
      result = statement.executeQuery();

      List<EmployeeEntity> employeeList = new ArrayList<>();
      while (result.next()) {
        EmployeeEntity employeeEntity = EmployeeMapper.toEntity(result);
        employeeList.add(employeeEntity);
      }
      return employeeList;
    } catch (SQLException exception) {
      throw new RuntimeException("Error to find all employees: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

  @Override
  public EmployeeEntity findByCode(int code) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      statement = connection.prepareStatement("SELECT code, name, contract_date, department_code FROM employees WHERE code = ?");
      statement.setInt(1, code);
      result = statement.executeQuery();

      EmployeeEntity employee = null;
      if (result.next())
        employee = EmployeeMapper.toEntity(result);

      if(employee != null)
        return employee;

      throw new IllegalArgumentException("No such employee with code" + code);

    } catch (SQLException exception) {
      throw new RuntimeException("Error to find employee by code: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }
}
