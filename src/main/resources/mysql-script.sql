CREATE USER 'java7_user'@'%' IDENTIFIED BY 'qwerty';
GRANT ALL PRIVILEGES ON *.* TO 'java7_user'@'%' WITH GRANT OPTION;
CREATE schema java7db;

USE java7db;

CREATE TABLE IF NOT EXISTS departments (
    code bigint auto_increment,
    name varchar(25) NOT NULL,
    location varchar(25) NOT NULL,

    PRIMARY KEY (code)
);

CREATE TABLE IF NOT EXISTS employees (
    code bigint auto_increment,
    name varchar(25) NOT NULL,
    document_identification bigint NOT NULL,
    contract_date date NOT NULL,
    contract_type varchar(25) NOT NULL,
	department_code bigint NOT NULL,
    
    PRIMARY KEY (code),
    FOREIGN KEY (department_code) REFERENCES departments(code)
);

INSERT INTO departments (name, location) VALUES ('Ventas', 'Buenos Aires');
INSERT INTO departments (name, location) VALUES ('Compras', 'Buenos Aires');
INSERT INTO departments (name, location) VALUES ('RRHH', 'La Plata');

INSERT INTO employees (name, document_identification, contract_date, contract_type, department_code) VALUES ('Juan', 76517368, '1990-7-09', 'honorario', 1);
INSERT INTO employees (name, document_identification, contract_date, contract_type, department_code) VALUES ('Alberto', 76517367, '1991-8-08', 'planilla', 3);
INSERT INTO employees (name, document_identification, contract_date, contract_type, department_code) VALUES ('Pedro', 76517366, CURDATE(), 'honorario', 1);
INSERT INTO employees (name, document_identification, contract_date, contract_type, department_code) VALUES ('Marcos', 76517365, '1993-10-06', 'planilla', 2);
INSERT INTO employees (name, document_identification, contract_date, contract_type, department_code) VALUES ('Jaime', 76517364, CURDATE(), 'planilla', 2);
INSERT INTO employees (name, document_identification, contract_date, contract_type, department_code) VALUES ('Pablo', 76517363, '1995-12-04', 'planilla', 1);

SELECT * FROM departments;
SELECT * FROM employees;

DROP TABLE IF EXISTS employees;


