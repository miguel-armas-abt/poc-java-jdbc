package com.demo.poc;

import com.demo.poc.dao.EmployeeDao;
import com.demo.poc.dao.EmployeeDaoImpl;
import com.demo.poc.dto.EmployeeDTO;
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
    EmployeeDTO employeeDto = new EmployeeDTO();
    employeeDto.setName("Miguel");
    employeeDto.setDocumentIdentification(76517368);
    employeeDto.setContractDate(new Date(System.currentTimeMillis()));
    employeeDto.setContractType("planilla");
    employeeDto.setDepartmentCode(1);
    employeeDao.save(employeeDto);

    System.out.println("\nFIND ALL");
    employeeDao.findAll().forEach(System.out::println);

    System.out.println("\nDELETE BY ID");
    employeeDao.deleteByCode(2);

    System.out.println("\nFIND ALL");
    employeeDao.findAll().forEach(System.out::println);

  }
}
