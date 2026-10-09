package com.practice.leavetracker.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record EmployeeWeeklyOffDto(Long id, Long empId, LocalDate startDate, DayOfWeek dayOfWeek) {
}
