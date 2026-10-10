package com.practice.leavetracker.dto;

import java.time.DayOfWeek;
import java.util.List;

public record UpdateWeeklyOffRequest(List<DayOfWeek> daysOff) {
}
