package com.practice.leavetracker.employee.service.impl;

import com.practice.leavetracker.employee.dto.EmployeeDto;
import com.practice.leavetracker.employee.entity.Employee;
import com.practice.leavetracker.exception.ResourceNotFoundException;
import com.practice.leavetracker.employee.mapper.EmployeeMapper;
import com.practice.leavetracker.employee.repository.EmployeeRepository;
import com.practice.leavetracker.employee.service.EmployeeService;
import com.practice.leavetracker.leaveBalance.service.LeaveBalanceService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final LeaveBalanceService leaveBalanceService;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = employeeMapper.toEntity(employeeDto);

        if (employeeDto.manager() != null) {

            Employee manager = employeeRepository.findById(employeeDto.manager())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Manager not found"));

            employee.setManager(manager);
        }

        Employee savedEmployee = employeeRepository.save(employee);
        leaveBalanceService.createInitialBalance(savedEmployee);

        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id " + id));
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long id ,EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id " + id));
        employee.setFirstName(employeeDto.firstName());
        employee.setLastName(employeeDto.lastName());
        employee.setEmail(employeeDto.email());
        employee.setEmploymentType(employeeDto.employmentType());
        employee.setDepartment(employeeDto.department());
        employee.setRole(employeeDto.role());
        employee.setProbationEndDate(employeeDto.probationEndDate());
        employee.setDesignation(employeeDto.designation());


        Employee manager = employeeRepository.findById(employeeDto.manager())
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));

        employee.setManager(manager);
        return null;
    }
    //(Long empId , String firstName , String lastName
//,String email,LocalDate joiningDate ,String employmentType,String department
//,String role,LocalDate probationEndDate,String designation , Long manager

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));

        employee.setActive(false);
        employeeRepository.save(employee);
    }
}