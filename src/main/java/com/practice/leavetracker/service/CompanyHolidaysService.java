package com.practice.leavetracker.service;

import com.practice.leavetracker.dto.CompanyHolidaysDto;

import java.time.LocalDate;
import java.util.List;

public interface CompanyHolidaysService {

//    CompanyHolidaysDto createCompanyHolidays(CompanyHolidaysDto companyHolidaysDto);

    List<CompanyHolidaysDto> getAllCompanyHolidays();

//    CompanyHolidaysDto getCompanyHolidayByDate(LocalDate holidayDate);


}
