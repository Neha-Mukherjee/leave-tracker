package com.practice.leavetracker.service;

import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.dto.LeaveBalanceDto;

import java.math.BigDecimal;

public interface LeaveBalanceService {
    LeaveBalanceDto createInitialBalance(Employee employee);
    LeaveBalanceDto getLeaveBalance(Long empId, Integer year);
    void validateLeaveBalance(Long  empId, String leaveType, BigDecimal requestedDuration);
    void deductLeaveBalance(Long  empId, String leaveType, BigDecimal duration);
}
