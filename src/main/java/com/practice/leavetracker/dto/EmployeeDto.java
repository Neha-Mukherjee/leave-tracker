package com.practice.leavetracker.dto;

import com.practice.leavetracker.entity.Employee;
import jakarta.mail.Address;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public record EmployeeDto(Long empId , String firstName , String lastName
,String email,LocalDate joiningDate ,String employmentType,String department
,String role,LocalDate probationEndDate,String designation , Long manager) {
}

//@Column(name = "emp_id")
//private Long empId;
//@Column(name = "first_name",nullable = false)
//private String firstName;
//@Column(name = "last_name",nullable = false)
//private String lastName;
//@Column(name = "email",nullable = false)
//private String email;
//@Column(name = "joining_date",nullable = false)
//private LocalDate joiningDate;
//@Column(name = "employment_type",nullable = false)
//private String employmentType;
//@Column(nullable = false)
//private String department;
//@Column(nullable = false)
//private String role;
//@Column(name="probation_end_date",nullable = false)
//private LocalDate probationEndDate;
//@Column(nullable = false)
//private String designation;
//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name="manager_id")
//private Employee manager;