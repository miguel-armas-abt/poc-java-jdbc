package com.demo.poc.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Patrón de diseño: Data Transfer Object (DTO)
 * <p>
 * <br/> Clase DTO que contiene datos en un modelo simple, sin lógica de negocio ni tecnologías de infraestructura.
 * Este modelo permite transferir sus datos entre diferentes componentes del sistema.<br/>
 */
public class EmployeeDTO implements Serializable {

  private int code;
  private String name;
  private int documentIdentification;
  private Date contractDate;
  private String contractType;
  private int departmentCode;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDocumentIdentification() {
    return documentIdentification;
  }

  public void setDocumentIdentification(int documentIdentification) {
    this.documentIdentification = documentIdentification;
  }

  public Date getContractDate() {
    return contractDate;
  }

  public void setContractDate(Date contractDate) {
    this.contractDate = contractDate;
  }

  public String getContractType() {
    return contractType;
  }

  public void setContractType(String contractType) {
    this.contractType = contractType;
  }

  public int getDepartmentCode() {
    return departmentCode;
  }

  public void setDepartmentCode(int departmentCode) {
    this.departmentCode = departmentCode;
  }

  @Override
  public String toString() {
    return code + "," + name + "," + contractDate + "," + departmentCode;
  }

}