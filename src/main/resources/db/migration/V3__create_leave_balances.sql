CREATE TABLE leave_balances (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                employee_id BIGINT NOT NULL,

                                year INT NOT NULL,

                                pl_total DECIMAL(5,2) NOT NULL DEFAULT 0.00,
                                pl_used DECIMAL(5,2) NOT NULL DEFAULT 0.00,

                                cl_total DECIMAL(5,2) NOT NULL DEFAULT 0.00,
                                cl_used DECIMAL(5,2) NOT NULL DEFAULT 0.00,

                                sl_total DECIMAL(5,2) NOT NULL DEFAULT 0.00,
                                sl_used DECIMAL(5,2) NOT NULL DEFAULT 0.00,

                                CONSTRAINT fk_leave_balance_employee
                                    FOREIGN KEY (employee_id)
                                        REFERENCES employees(emp_id),

                                CONSTRAINT uk_employee_year
                                    UNIQUE (employee_id, year)
);