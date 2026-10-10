package com.practice.leavetracker.service;

import com.practice.leavetracker.dto.CompanyHolidaysDto;
import com.practice.leavetracker.dto.EmployeeHolidaySelectionDto;
import com.practice.leavetracker.dto.EmployeeHolidaySelectionRequestDto;

import java.time.LocalDate;
import java.util.List;

public interface CompanyHolidaysService {

//    CompanyHolidaysDto createCompanyHolidays(CompanyHolidaysDto companyHolidaysDto);

    List<CompanyHolidaysDto> getAllCompanyHolidays();

//    CompanyHolidaysDto getCompanyHolidayByDate(LocalDate holidayDate);

    List<EmployeeHolidaySelectionDto> getEmployeeSelections(Long empId,Integer year);

    List<EmployeeHolidaySelectionDto> saveEmployeeSelections(Long  empId, Integer year, EmployeeHolidaySelectionRequestDto request);


}
