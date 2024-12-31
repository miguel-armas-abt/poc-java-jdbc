package com.demo.poc;

import com.demo.poc.dao.EmployeeDao;
import com.demo.poc.dao.EmployeeDaoImpl;
import com.demo.poc.entity.EmployeeEntity;
import java.sql.Date;
import java.sql.SQLException;

public class Application {

  public static void main(String[] args) throws SQLException {
    EmployeeDao employeeDao = new EmployeeDaoImpl();

    System.out.println("\nFIND BY CODE");
    System.out.println(employeeDao.findByCode(2));

    System.out.println("\nFIND ALL");
    employeeDao.findAll().forEach(System.out::println);

    System.out.println("\nSAVE");
    EmployeeEntity employeeEntity = new EmployeeEntity();
    employeeEntity.setName("Miguel");
    employeeEntity.setDocumentIdentification(76517368);
    employeeEntity.setContractDate(new Date(System.currentTimeMillis()));
    employeeEntity.setContractType("planilla");
    employeeEntity.setDepartmentCode(1);
    employeeDao.save(employeeEntity);

    System.out.println("\nFIND ALL");
    employeeDao.findAll().forEach(System.out::println);

    System.out.println("\nDELETE BY ID");
    employeeDao.deleteByCode(2);

    System.out.println("\nFIND ALL");
    employeeDao.findAll().forEach(System.out::println);

  }
}
