package com.practice.leavetracker.mapper;

import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.dto.LeaveBalanceDto;
import com.practice.leavetracker.entity.LeaveBalance;
import org.springframework.stereotype.Component;

@Component
public class LeaveBalanceMapper {

    public LeaveBalanceDto toDto(LeaveBalance leaveBalance) {
        return new LeaveBalanceDto(leaveBalance.getId(), leaveBalance.getEmployee().getEmpId(),
                leaveBalance.getYear(),
                leaveBalance.getPlTotal(),
                leaveBalance.getPlUsed(),
                leaveBalance.getClTotal(),
                leaveBalance.getClUsed(),
                leaveBalance.getSlTotal(),
                leaveBalance.getSlUsed());

    }
    public LeaveBalance toEntity(LeaveBalanceDto leaveBalanceDto, Employee employee) {
        return new LeaveBalance(
                leaveBalanceDto.id(),
                employee,
                leaveBalanceDto.year(),
                leaveBalanceDto.plTotal(),
                leaveBalanceDto.plUsed(),
                leaveBalanceDto.clTotal(),
                leaveBalanceDto.clUsed(),
                leaveBalanceDto.slTotal(),
                leaveBalanceDto.slUsed()
        );
    }

}
