package com.demo.poc.entity;

import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity implements Serializable {

  private int code;
  private String name;
  private int documentIdentification;
  private Date contractDate;
  private String contractType;
  private int departmentCode;
}
