package com.practice.leavetracker.leaveBalance.service;

import com.practice.leavetracker.employee.entity.Employee;

import java.math.BigDecimal;

public interface LeaveBalanceCalculationService {

    BigDecimal calculatePL(Employee employee);
    BigDecimal calculateCL(Employee employee);
    BigDecimal calculateSL(Employee employee);
}
