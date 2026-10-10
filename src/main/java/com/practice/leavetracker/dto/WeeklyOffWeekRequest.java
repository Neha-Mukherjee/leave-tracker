package com.practice.leavetracker.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public record WeeklyOffWeekRequest(LocalDate weekStartDate, List<DayOfWeek> daysOff) {
}
