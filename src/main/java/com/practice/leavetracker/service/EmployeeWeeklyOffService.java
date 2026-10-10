package com.practice.leavetracker.service;

import com.practice.leavetracker.dto.EmployeeWeeklyOffDto;
import com.practice.leavetracker.dto.UpdateWeeklyOffRequest;
import com.practice.leavetracker.dto.WeeklyOffAssignmentRequest;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeWeeklyOffService {
    List<EmployeeWeeklyOffDto> assignWeeklyOff(WeeklyOffAssignmentRequest request);
    List<EmployeeWeeklyOffDto> getWeeklyOffs(Long empId);
    List<EmployeeWeeklyOffDto> updateWeeklyOff(Long empId , LocalDate weekStartDate, UpdateWeeklyOffRequest request);
}
