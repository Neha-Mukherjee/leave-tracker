package com.practice.leavetracker.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employees")
public class Employee {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "joining_date",nullable = false)
    private LocalDate joiningDate;
    @Column(name = "employment_type",nullable = false)
    private String employmentType;
    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private String role;
    @Column(name="probation_end_date",nullable = false)
    private LocalDate probationEndDate;
    @Column(nullable = false)
    private String designation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private Employee manager;
    @Column(nullable = false)
    private boolean active=true;
}
