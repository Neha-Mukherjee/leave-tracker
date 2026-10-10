package com.practice.leavetracker.controller;

import com.practice.leavetracker.dto.EmployeeWeeklyOffDto;
import com.practice.leavetracker.dto.UpdateWeeklyOffRequest;
import com.practice.leavetracker.dto.WeeklyOffAssignmentRequest;
import com.practice.leavetracker.service.EmployeeWeeklyOffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leave-tracker/weekly-offs")
@RequiredArgsConstructor
public class EmployeeWeeklyoffController {

    private final EmployeeWeeklyOffService weeklyOffService;

    @PostMapping
    public ResponseEntity<List<EmployeeWeeklyOffDto>> assignWeeklyOffs(@RequestBody WeeklyOffAssignmentRequest request) {


        return ResponseEntity.status(HttpStatus.CREATED).body(weeklyOffService.assignWeeklyOff(request));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<List<EmployeeWeeklyOffDto>> getWeeklyOffs(@PathVariable Long empId) {

        return ResponseEntity.ok(weeklyOffService.getWeeklyOffs(empId));
    }

    @PutMapping("/{empId}/{weekStartDate}")
    public ResponseEntity<List<EmployeeWeeklyOffDto>> updateWeeklyOffs(
            @PathVariable Long empId,
            @PathVariable LocalDate weekStartDate,
            @RequestBody UpdateWeeklyOffRequest request) {


        return ResponseEntity.ok(weeklyOffService.updateWeeklyOff(empId, weekStartDate, request));
    }
}

