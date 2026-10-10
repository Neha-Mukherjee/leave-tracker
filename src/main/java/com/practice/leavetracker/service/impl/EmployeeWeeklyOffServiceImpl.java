package com.practice.leavetracker.service.impl;

import com.practice.leavetracker.dto.EmployeeWeeklyOffDto;
import com.practice.leavetracker.dto.UpdateWeeklyOffRequest;
import com.practice.leavetracker.dto.WeeklyOffAssignmentRequest;
import com.practice.leavetracker.dto.WeeklyOffWeekRequest;
import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.entity.EmployeeWeeklyOff;
import com.practice.leavetracker.exception.ResourceNotFoundException;
import com.practice.leavetracker.mapper.EmployeeWeeklyOffMapper;
import com.practice.leavetracker.repository.EmployeeRepository;
import com.practice.leavetracker.repository.EmployeeWeeklyoffRepository;
import com.practice.leavetracker.service.EmployeeWeeklyOffService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeWeeklyOffServiceImpl implements EmployeeWeeklyOffService {

    private final EmployeeWeeklyoffRepository employeeWeeklyoffRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeWeeklyOffMapper employeeWeeklyOffMapper;

    @Override
    @Transactional
    public List<EmployeeWeeklyOffDto> assignWeeklyOff(WeeklyOffAssignmentRequest request) {
        if (request.empId() == null || request.schedules() == null || request.schedules().isEmpty()) {
            throw new IllegalArgumentException("Employee ID and schedules are required");
        }
        Employee employee = employeeRepository.findById(request.empId()).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        List<EmployeeWeeklyOff> entries = new ArrayList<>();

        for (WeeklyOffWeekRequest schedule : request.schedules()) {

            LocalDate weekStart = schedule.weekStartDate();

            validateWeekStart(weekStart);

            if (weekStart.isBefore(currentWeekStart())) {
                throw new IllegalArgumentException(
                        "Cannot assign weekly offs for a past week");
            }

            if (employeeWeeklyoffRepository
                    .existsByEmployeeEmpIdAndWeekStartDate(
                            request.empId(), weekStart)) {
                throw new IllegalArgumentException(
                        "Schedule already exists for this week");
            }

            if (schedule.daysOff() == null || schedule.daysOff().isEmpty()
                    || schedule.daysOff().size()
                    != new HashSet<>(schedule.daysOff()).size()) {
                throw new IllegalArgumentException(
                        "Provide valid, non-duplicate weekly-off days");
            }

            for (DayOfWeek day : schedule.daysOff()) {
                entries.add(new EmployeeWeeklyOff(
                        null, employee, weekStart, day));
            }

        }
        return employeeWeeklyoffRepository.saveAll(entries)
                .stream()
                .map(employeeWeeklyOffMapper::toDto)
                .toList();
    }

    @Override
    public List<EmployeeWeeklyOffDto> getWeeklyOffs(Long empId) {
        if (!employeeRepository.existsById(empId)) {
            throw new ResourceNotFoundException("Employee not found");
        }

        return employeeWeeklyoffRepository
                .findByEmployeeEmpIdOrderByWeekStartDateAsc(empId)
                .stream()
                .map(employeeWeeklyOffMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public List<EmployeeWeeklyOffDto> updateWeeklyOff(Long empId, LocalDate weekStartDate, UpdateWeeklyOffRequest request) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        validateWeekStart(weekStartDate);

        if (!weekStartDate.isAfter(currentWeekStart())) {
            throw new IllegalArgumentException(
                    "Only future schedules can be updated");
        }

        if (request.daysOff() == null || request.daysOff().isEmpty()
                || request.daysOff().size()
                != new HashSet<>(request.daysOff()).size()) {
            throw new IllegalArgumentException(
                    "Provide valid, non-duplicate weekly-off days");
        }

        if (!employeeWeeklyoffRepository
                .existsByEmployeeEmpIdAndWeekStartDate(empId, weekStartDate)) {
            throw new ResourceNotFoundException("Schedule not found");
        }

        employeeWeeklyoffRepository
                .deleteByEmployeeEmpIdAndWeekStartDate(empId, weekStartDate);

        employeeWeeklyoffRepository.flush();

        List<EmployeeWeeklyOff> entries = request.daysOff().stream()
                .map(day -> new EmployeeWeeklyOff(
                        null, employee, weekStartDate, day))
                .toList();

        return employeeWeeklyoffRepository.saveAll(entries)
                .stream()
                .map(employeeWeeklyOffMapper::toDto)
                .toList();
    }

    private void validateWeekStart(LocalDate date) {
        if (date == null || date.getDayOfWeek() != DayOfWeek.MONDAY) {
            throw new IllegalArgumentException(
                    "Week start date must be a Monday");
        }
    }

    private LocalDate currentWeekStart() {
        return LocalDate.now().with(
                java.time.temporal.TemporalAdjusters
                        .previousOrSame(DayOfWeek.MONDAY));
    }
}

