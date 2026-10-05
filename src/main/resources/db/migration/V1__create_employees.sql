CREATE TABLE employees (
                           emp_id BIGINT AUTO_INCREMENT PRIMARY KEY,

                           first_name VARCHAR(100) NOT NULL,
                           last_name VARCHAR(100) NOT NULL,
                           email VARCHAR(255) NOT NULL UNIQUE,

                           joining_date DATE NOT NULL,
                           employment_type VARCHAR(20) NOT NULL,
                           department VARCHAR(100) NOT NULL,
                           role VARCHAR(50) NOT NULL,

                           probation_end_date DATE NOT NULL,
                           designation VARCHAR(50) NOT NULL,

                           manager_id BIGINT,

                           CONSTRAINT fk_employee_manager
                               FOREIGN KEY (manager_id)
                                   REFERENCES employees(emp_id)
);