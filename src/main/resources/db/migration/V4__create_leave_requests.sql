CREATE TABLE leave_requests (

                                id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                employee_id BIGINT NOT NULL,

                                leave_type VARCHAR(20) NOT NULL,

                                start_date DATE NOT NULL,

                                end_date DATE NOT NULL,

                                duration DECIMAL(5,2) NOT NULL,

                                reason VARCHAR(500) NOT NULL,

                                status VARCHAR(20) NOT NULL,

                                applied_at DATETIME NOT NULL,

                                CONSTRAINT fk_leave_request_employee
                                    FOREIGN KEY (employee_id)
                                        REFERENCES employees(emp_id)
);