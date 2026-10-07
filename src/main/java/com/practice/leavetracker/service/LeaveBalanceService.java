package com.practice.leavetracker.service;

import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.dto.LeaveBalanceDto;

public interface LeaveBalanceService {
    LeaveBalanceDto createInitialBalance(Employee employee);
    LeaveBalanceDto getLeaveBalance(Long empId, Integer year);
}
