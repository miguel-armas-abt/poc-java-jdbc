package com.demo.poc.dao;

import com.demo.poc.dto.EmployeeDTO;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

  List<EmployeeDTO> findAll();

  EmployeeDTO findByCode(int code);

  void save(EmployeeDTO employee);

  void deleteByCode(int code) throws SQLException;
}
