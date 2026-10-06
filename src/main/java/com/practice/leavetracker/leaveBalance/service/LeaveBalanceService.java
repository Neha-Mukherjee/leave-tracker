package com.practice.leavetracker.leaveBalance.service;

import com.practice.leavetracker.employee.entity.Employee;
import com.practice.leavetracker.leaveBalance.dto.LeaveBalanceDto;

public interface LeaveBalanceService {
    LeaveBalanceDto createInitialBalance(Employee employee);
    LeaveBalanceDto getLeaveBalance(Long empId, Integer year);
}
