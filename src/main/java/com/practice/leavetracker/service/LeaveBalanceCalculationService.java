package com.practice.leavetracker.service;

import com.practice.leavetracker.entity.Employee;

import java.math.BigDecimal;

public interface LeaveBalanceCalculationService {

    BigDecimal calculatePL(Employee employee);
    BigDecimal calculateCL(Employee employee);
    BigDecimal calculateSL(Employee employee);
}
