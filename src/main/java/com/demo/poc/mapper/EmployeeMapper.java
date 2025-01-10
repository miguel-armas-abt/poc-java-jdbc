package com.demo.poc.mapper;

import com.demo.poc.entity.EmployeeEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeMapper {

  public static EmployeeEntity toEntity (ResultSet result) throws SQLException {
    EmployeeEntity employee = new EmployeeEntity();
    employee.setCode(result.getInt("code"));
    employee.setName(result.getString("name"));
    employee.setContractDate(result.getDate("contract_date"));
    employee.setDepartmentCode(result.getInt("department_code"));
    return employee;
  }
}
