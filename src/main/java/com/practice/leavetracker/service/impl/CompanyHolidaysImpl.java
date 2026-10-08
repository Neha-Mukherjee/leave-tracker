package com.practice.leavetracker.service.impl;

import com.practice.leavetracker.dto.CompanyHolidaysDto;
import com.practice.leavetracker.entity.CompanyHolidays;
import com.practice.leavetracker.exception.ResourceNotFoundException;
import com.practice.leavetracker.mapper.CompanyHolidaysMapper;
import com.practice.leavetracker.repository.CompanyHolidaysRepository;
import com.practice.leavetracker.service.CompanyHolidaysService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyHolidaysImpl implements CompanyHolidaysService {

    private final CompanyHolidaysRepository companyHolidaysRepository;
    private final CompanyHolidaysMapper companyHolidaysMapper;


//
//    @Override
//    public CompanyHolidaysDto createCompanyHolidays(CompanyHolidaysDto companyHolidaysDto) {
//        CompanyHolidays companyHolidays = companyHolidaysRepository.save(companyHolidaysMapper.toEntity(companyHolidaysDto));
//        return companyHolidaysMapper.toDto(companyHolidays);
//    }

    @Override
    public List<CompanyHolidaysDto> getAllCompanyHolidays() {
        List<CompanyHolidays> companyHolidays =companyHolidaysRepository.findAll();
        return companyHolidays.stream().map(companyHolidaysMapper::toDto).toList();
    }

//    @Override
//    public CompanyHolidaysDto getCompanyHolidayByDate(LocalDate holidayDate) {
//        CompanyHolidays companyHolidays=companyHolidaysRepository.findBy(id).orElseThrow(()->new ResourceNotFoundException("Company holiday not found"));
//        return companyHolidaysMapper.toDto(companyHolidays);
//    }
}
