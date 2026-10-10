package com.practice.leavetracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="leave_requests")
public class LeaveRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;
    @Column(name = "leave_type", nullable = false)
    private String leaveType;
    @Column(name="start_date",nullable = false)
    private LocalDate startDate;
    @Column(name="end_date",nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private BigDecimal duration;
    @Column(nullable = false)
    private String reason;
    @Column(nullable = false)
    private String status;
    @Column(name = "applied_at",nullable = false)
    private LocalDateTime appliedAt;
    @Column(name = "medical_document_name")
    private String medicalDocumentName;

    @Column(name = "medical_document_path")
    private String medicalDocumentPath;


}
