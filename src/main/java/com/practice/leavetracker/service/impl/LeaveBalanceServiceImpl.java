package com.practice.leavetracker.service.impl;

import com.practice.leavetracker.dto.LeaveBalanceDto;
import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.entity.LeaveBalance;
import com.practice.leavetracker.exception.ResourceNotFoundException;
import com.practice.leavetracker.mapper.LeaveBalanceMapper;
import com.practice.leavetracker.repository.LeaveBalanceRepository;
import com.practice.leavetracker.service.LeaveBalanceCalculationService;
import com.practice.leavetracker.service.LeaveBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
    @RequiredArgsConstructor
    public class LeaveBalanceServiceImpl implements LeaveBalanceService {

        private final LeaveBalanceRepository leaveBalanceRepository;
        private final LeaveBalanceMapper leaveBalanceMapper;
        private final LeaveBalanceCalculationService calculationService;


        @Override
        public LeaveBalanceDto createInitialBalance(Employee employee) {

            BigDecimal plTotal=calculationService.calculatePL(employee);
            BigDecimal clTotal=calculationService.calculateCL(employee);
            BigDecimal slTotal=calculationService.calculateSL(employee);
            LeaveBalance leaveBalance = new LeaveBalance(
                    null,
                    employee,
                    employee.getJoiningDate().getYear(),
                    plTotal,
                    BigDecimal.ZERO,
                    clTotal,
                    BigDecimal.ZERO,
                    slTotal,
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

        @Override
        public void validateLeaveBalance(Long empId, String leaveType, BigDecimal requestedDuration) {
            int year = LocalDate.now().getYear();
            LeaveBalance balance = leaveBalanceRepository.findByEmployeeEmpIdAndYear(empId, year).orElseThrow(()->new ResourceNotFoundException("leave balance not available"));
            BigDecimal total ;
            BigDecimal used;
            switch (leaveType) {
                case "PL"->{
                    total = balance.getPlTotal();
                    used = balance.getPlUsed();
                }
                case "CL"->{
                    total = balance.getClTotal();
                    used = balance.getClUsed();
                }
                case "SL"->{
                    total = balance.getSlTotal();
                    used = balance.getSlUsed();
                }
                default -> throw new IllegalArgumentException("Invalid leave type");
            }
            BigDecimal available = total.subtract(used);
            if(requestedDuration.compareTo(available)>0){
                throw new ResourceNotFoundException("Insufficient"+leaveType +"balance. Available: "+available);
            }
        }

    @Override
    public void deductLeaveBalance(Long empId, String leaveType, BigDecimal duration) {
        int year = LocalDate.now().getYear();
        LeaveBalance balance = leaveBalanceRepository.findByEmployeeEmpIdAndYear(empId, year).orElseThrow(()->new ResourceNotFoundException("leave balance not available"));
        switch (leaveType) {
            case "PL" ->
                    balance.setPlUsed(balance.getPlUsed().add(duration));
            case "CL" ->
                    balance.setClUsed(balance.getClUsed().add(duration));
            case "SL" ->
                    balance.setSlUsed(balance.getSlUsed().add(duration));
            default ->
                    throw new IllegalArgumentException("Invalid leave type");
        }

        leaveBalanceRepository.save(balance);
    }

}
