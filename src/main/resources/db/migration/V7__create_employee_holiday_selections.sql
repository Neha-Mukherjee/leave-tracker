CREATE TABLE employee_holiday_selections (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             employee_id BIGINT NOT NULL,
                                             holiday_id BIGINT NOT NULL,

                                             CONSTRAINT fk_selection_employee
                                                 FOREIGN KEY (employee_id)
                                                     REFERENCES employees(emp_id),

                                             CONSTRAINT fk_selection_holiday
                                                 FOREIGN KEY (holiday_id)
                                                     REFERENCES company_holidays(id),

                                             CONSTRAINT uk_employee_holiday
                                                 UNIQUE (employee_id, holiday_id)
);