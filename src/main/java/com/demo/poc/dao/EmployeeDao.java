package com.demo.poc.dao;

import com.demo.poc.entity.EmployeeEntity;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

  List<EmployeeEntity> findAll();

  EmployeeEntity findByCode(int code);

  void save(EmployeeEntity employee);

  void deleteByCode(int code) throws SQLException;
}
