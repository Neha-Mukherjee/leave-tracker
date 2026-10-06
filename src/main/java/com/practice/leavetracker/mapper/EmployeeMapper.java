package com.practice.leavetracker.mapper;

import com.practice.leavetracker.dto.EmployeeDto;
import com.practice.leavetracker.entity.Employee;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMapper
{
    public EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(employee.getEmpId(), employee.getFirstName(), employee.getLastName(), employee.getEmail()
                ,employee.getJoiningDate(), employee.getEmploymentType(), employee.getDepartment(), employee.getRole(),
                employee.getProbationEndDate(), employee.getDesignation(),
                employee.getManager() != null ? employee.getManager().getEmpId() : null);
    }

    public Employee toEntity(EmployeeDto employeeDto) {
        return new Employee(employeeDto.empId(), employeeDto.firstName(), employeeDto.lastName(),
                employeeDto.email(),employeeDto.joiningDate(), employeeDto.employmentType(),
                employeeDto.department(), employeeDto.role(), employeeDto.probationEndDate(), employeeDto.designation(),null);
    }
}
//Long empId , String firstName , String lastName
//,String email,LocalDate joiningDate ,String employmentType,String department
//,String role,LocalDate probationEndDate,String designation