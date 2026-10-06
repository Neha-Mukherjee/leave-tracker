package com.practice.leavetracker.leaveBalance.service.impl;

import com.practice.leavetracker.employee.entity.Employee;
import com.practice.leavetracker.exception.ResourceNotFoundException;
import com.practice.leavetracker.leaveBalance.dto.LeaveBalanceDto;
import com.practice.leavetracker.leaveBalance.entity.LeaveBalance;
import com.practice.leavetracker.leaveBalance.mapper.LeaveBalanceMapper;
import com.practice.leavetracker.leaveBalance.repository.LeaveBalanceRepository;
import com.practice.leavetracker.leaveBalance.service.LeaveBalanceCalculationService;
import com.practice.leavetracker.leaveBalance.service.LeaveBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

    private final LeaveBalanceRepository leaveBalanceRepository;
    private final LeaveBalanceMapper leaveBalanceMapper;
    private final LeaveBalanceCalculationService calculationService;


    @Override
    public LeaveBalanceDto createInitialBalance(Employee employee) {

        BigDecimal p1=calculationService.calculatePL(employee);
        BigDecimal p2=calculationService.calculateCL(employee);
        BigDecimal p3=calculationService.calculateSL(employee);
        LeaveBalance leaveBalance = new LeaveBalance(
                null,
                employee,
                employee.getJoiningDate().getYear(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );

        LeaveBalance savedBalance =
                leaveBalanceRepository.save(leaveBalance);

        return leaveBalanceMapper.toDto(savedBalance);
    }

    @Override
    public LeaveBalanceDto getLeaveBalance(Long empId, Integer year) {
        LeaveBalance leaveBalance=leaveBalanceRepository.findByEmployeeEmpIdAndYear(empId, year)
                .orElseThrow();
        return leaveBalanceMapper.toDto(leaveBalance);
    }
}
