package com.practice.leavetracker.controller;

import com.practice.leavetracker.dto.CompanyHolidaysDto;
import com.practice.leavetracker.dto.EmployeeHolidaySelectionDto;
import com.practice.leavetracker.dto.EmployeeHolidaySelectionRequestDto;
import com.practice.leavetracker.entity.CompanyHolidays;
import com.practice.leavetracker.repository.CompanyHolidaysRepository;
import com.practice.leavetracker.service.CompanyHolidaysService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-tracker/company-holidays")
@RequiredArgsConstructor
public class CompanyHolidaysController {
    private final CompanyHolidaysService companyHolidaysService;



    //create company holiday
//    @PostMapping
//    public ResponseEntity<CompanyHolidaysDto> createCompanyHolidays(@RequestBody CompanyHolidaysDto companyHolidaysDto) {
//        companyHolidaysService.createCompanyHolidays(companyHolidaysDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(companyHolidaysDto);
//    }
    //get all holidays
    @GetMapping
    public ResponseEntity<List<CompanyHolidaysDto>> getAllCompanyHolidays() {
        List<CompanyHolidaysDto> companyHolidays=companyHolidaysService.getAllCompanyHolidays();
        return ResponseEntity.ok(companyHolidays);
    }

    //get the employee selection
    @GetMapping("/selections/{empId}")
    public ResponseEntity<List<EmployeeHolidaySelectionDto>> getEmployeeSelections(@PathVariable("empId") Long empId, @RequestParam Integer year) {
        return ResponseEntity.ok(companyHolidaysService.getEmployeeSelections(empId, year));
    }

    //save or change employee selected holiday
    @PutMapping("/selections/{empId}")
    public ResponseEntity<List<EmployeeHolidaySelectionDto>> saveEmployeeSelection(@PathVariable Long empId,@RequestParam Integer year, @RequestBody EmployeeHolidaySelectionRequestDto request) {
        return ResponseEntity.ok(companyHolidaysService.saveEmployeeSelections(empId,year,request));
    }
}