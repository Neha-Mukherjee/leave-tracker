package com.practice.leavetracker.service.impl;

import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.service.LeaveBalanceCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class LeaveBalanceCalculationServiceImpl implements LeaveBalanceCalculationService {





    @Override
    public BigDecimal calculatePL(Employee employee) {

        int joiningMonth = employee.getJoiningDate().getMonthValue();

        int eligibleMonths= 12-joiningMonth+1;
        return BigDecimal.valueOf(eligibleMonths).multiply(BigDecimal.valueOf(1.5));
    }

    @Override
    public BigDecimal calculateCL(Employee employee) {
        int joiningMonth = employee.getJoiningDate().getMonthValue();

        int eligibleMonths = 12 - joiningMonth + 1;

        BigDecimal cl = BigDecimal.valueOf(7)
                .multiply(BigDecimal.valueOf(eligibleMonths))
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.DOWN);

        // Round down to nearest 0.5
        cl = cl.multiply(BigDecimal.valueOf(2))
                .setScale(0, RoundingMode.DOWN)
                .divide(BigDecimal.valueOf(2));

        return cl;
    }

    @Override
    public BigDecimal calculateSL(Employee employee) {
        int joiningMonth = employee.getJoiningDate().getMonthValue();

        int eligibleMonths = 12 - joiningMonth + 1;

        BigDecimal sl = BigDecimal.valueOf(7)
                .multiply(BigDecimal.valueOf(eligibleMonths))
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.DOWN);

        // Round down to nearest 0.5
        sl = sl.multiply(BigDecimal.valueOf(2))
                .setScale(0, RoundingMode.DOWN)
                .divide(BigDecimal.valueOf(2));

        return sl;
    }
}
