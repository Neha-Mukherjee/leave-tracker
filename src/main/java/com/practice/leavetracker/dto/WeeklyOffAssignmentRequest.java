package com.practice.leavetracker.dto;

import java.util.List;

public record WeeklyOffAssignmentRequest(Long empId , List<WeeklyOffWeekRequest> schedules) {
}
