package com.practice.leavetracker.service.impl;

import com.practice.leavetracker.dto.CompanyHolidaysDto;
import com.practice.leavetracker.dto.EmployeeHolidaySelectionDto;
import com.practice.leavetracker.dto.EmployeeHolidaySelectionRequestDto;
import com.practice.leavetracker.entity.CompanyHolidays;
import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.entity.EmployeeHolidaySelection;
import com.practice.leavetracker.exception.ResourceNotFoundException;
import com.practice.leavetracker.mapper.CompanyHolidaysMapper;
import com.practice.leavetracker.mapper.EmployeeHolidaySelectionMapper;
import com.practice.leavetracker.repository.CompanyHolidaysRepository;
import com.practice.leavetracker.repository.EmployeeHolidaySelectionRepository;
import com.practice.leavetracker.repository.EmployeeRepository;
import com.practice.leavetracker.service.CompanyHolidaysService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyHolidaysImpl implements CompanyHolidaysService {

    private final CompanyHolidaysRepository companyHolidaysRepository;
    private final CompanyHolidaysMapper companyHolidaysMapper;
    private final EmployeeRepository employeeRepository;
    private final EmployeeHolidaySelectionRepository selectionRepository;
    private final EmployeeHolidaySelectionMapper selectionMapper;



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

    @Override
    @Transactional
    public List<EmployeeHolidaySelectionDto> getEmployeeSelections(Long empId, Integer year) {
        if (!employeeRepository.existsById(empId)) {
            throw new ResourceNotFoundException("Employee not found");
        }

        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        return selectionRepository
                .findSelectionsForYear(empId, startDate, endDate)
                .stream()
                .map(selectionMapper::toDto)
                .toList();
    }

    @Override
    public List<EmployeeHolidaySelectionDto> saveEmployeeSelections(Long empId, Integer year, EmployeeHolidaySelectionRequestDto request) {
        if (request == null || request.holidayIds() == null) {
            throw new IllegalArgumentException("Holiday IDs are required");
        }

        List<Long> holidayIds = request.holidayIds();

        if (holidayIds.size() > 10) {
            throw new IllegalArgumentException(
                    "You can select a maximum of 10 holidays per year");
        }

        if (holidayIds.size()
                != new HashSet<>(holidayIds).size()) {
            throw new IllegalArgumentException(
                    "Duplicate holiday IDs are not allowed");
        }

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        List<CompanyHolidays> holidays =
                companyHolidaysRepository.findAllById(holidayIds);

        if (holidays.size() != holidayIds.size()) {
            throw new ResourceNotFoundException(
                    "One or more company holidays were not found");
        }

        for (CompanyHolidays holiday : holidays) {
            if (holiday.getHolidayDate().getYear() != year) {
                throw new IllegalArgumentException(
                        "All selected holidays must belong to year " + year);
            }
        }

        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        List<EmployeeHolidaySelection> existing = selectionRepository.findSelectionsForYear(empId, startDate, endDate);

        selectionRepository.deleteAll(existing);
        selectionRepository.flush();

        List<EmployeeHolidaySelection> newSelections = holidays.stream()
                .map(holiday -> new EmployeeHolidaySelection(
                        null, employee, holiday))
                .toList();

        return selectionRepository.saveAll(newSelections)
                .stream()
                .map(selectionMapper::toDto)
                .toList();
    }


//    @Override
//    public CompanyHolidaysDto getCompanyHolidayByDate(LocalDate holidayDate) {
//        CompanyHolidays companyHolidays=companyHolidaysRepository.findBy(id).orElseThrow(()->new ResourceNotFoundException("Company holiday not found"));
//        return companyHolidaysMapper.toDto(companyHolidays);
//    }
}
