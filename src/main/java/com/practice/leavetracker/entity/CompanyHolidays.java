package com.practice.leavetracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Company_Holidays")
public class CompanyHolidays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="holiday_name",nullable = false)
    private String holidayName;
    @Column(name="holiday_date",nullable = false)
    private LocalDate holidayDate;



}
