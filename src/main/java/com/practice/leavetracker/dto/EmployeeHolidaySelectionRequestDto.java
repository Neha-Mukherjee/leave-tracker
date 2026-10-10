package com.practice.leavetracker.dto;

import java.util.List;

public record EmployeeHolidaySelectionRequestDto(List<Long> holidayIds) {
}
