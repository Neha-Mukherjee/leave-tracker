package com.practice.leavetracker.mapper;

import com.practice.leavetracker.dto.CompanyHolidaysDto;
import com.practice.leavetracker.entity.CompanyHolidays;
import org.springframework.stereotype.Component;

@Component
public class CompanyHolidaysMapper {

    public CompanyHolidaysDto toDto(CompanyHolidays companyHolidays) {
        return new CompanyHolidaysDto(companyHolidays.getId(),companyHolidays.getHolidayName(),companyHolidays.getHolidayDate());
    }

    public CompanyHolidays toEntity(CompanyHolidaysDto companyHolidaysDto) {
        return new CompanyHolidays(companyHolidaysDto.id(),companyHolidaysDto.holidayName(),companyHolidaysDto.holidayDate());
    }
}
