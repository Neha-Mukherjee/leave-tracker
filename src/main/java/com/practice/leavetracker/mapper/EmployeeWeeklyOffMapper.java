package com.practice.leavetracker.mapper;

import com.practice.leavetracker.dto.EmployeeWeeklyOffDto;
import com.practice.leavetracker.entity.EmployeeWeeklyOff;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWeeklyOffMapper {
    public EmployeeWeeklyOffDto toDto(EmployeeWeeklyOff weeklyOff) {

        return new EmployeeWeeklyOffDto(
                weeklyOff.getId(),
                weeklyOff.getEmployee().getEmpId(),
                weeklyOff.getWeekStartDate(),
                weeklyOff.getDayofWeek()
        );
    }
}
//no toentity method because the sevice creates entities from employee and schedule info

