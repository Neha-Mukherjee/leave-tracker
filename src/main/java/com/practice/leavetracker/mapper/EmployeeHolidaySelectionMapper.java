package com.practice.leavetracker.mapper;

import com.practice.leavetracker.dto.EmployeeHolidaySelectionDto;
import com.practice.leavetracker.entity.EmployeeHolidaySelection;
import org.springframework.stereotype.Component;

@Component
public class EmployeeHolidaySelectionMapper {
    public EmployeeHolidaySelectionDto toDto(EmployeeHolidaySelection selection) {
        return new EmployeeHolidaySelectionDto(selection.getId(),selection.getEmployee().getEmpId(),selection.getCompanyHoliday().getId(),
                selection.getCompanyHoliday().getHolidayName(),selection.getCompanyHoliday().getHolidayDate());
    }
}
