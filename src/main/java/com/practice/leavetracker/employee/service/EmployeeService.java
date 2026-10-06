package com.practice.leavetracker.employee.service;

import com.practice.leavetracker.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {


    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto updateEmployee(Long id , EmployeeDto employeeDto);

    void deleteEmployee(Long id);
}
