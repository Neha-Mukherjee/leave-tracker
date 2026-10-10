CREATE TABLE employee_weekly_offs (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      employee_id BIGINT NOT NULL,
                                      week_start_date DATE NOT NULL,
                                      day_of_week VARCHAR(10) NOT NULL,

                                      CONSTRAINT fk_weekly_off_employee
                                          FOREIGN KEY (employee_id)
                                              REFERENCES employees(emp_id),

                                      CONSTRAINT uk_employee_week_day
                                          UNIQUE (employee_id, week_start_date, day_of_week)
);