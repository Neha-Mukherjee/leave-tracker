package com.practice.leavetracker.dto;

import java.time.LocalDate;

public record EmployeeHolidaySelectionDto(Long id, Long empId, Long holidayIds, String holidayName, LocalDate holidayDate) {
}
