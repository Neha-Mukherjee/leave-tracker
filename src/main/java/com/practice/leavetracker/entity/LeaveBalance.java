package com.practice.leavetracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="leave_balances")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    @Column(nullable = false)
    private Integer year;


    @Column(name = "pl_total", nullable = false)
    private BigDecimal plTotal;
    @Column(name = "pl_used", nullable = false)
    private BigDecimal plUsed;

    @Column(name = "cl_total", nullable = false)
    private BigDecimal clTotal;
    @Column(name = "cl_used", nullable = false)
    private BigDecimal clUsed;


    @Column(name = "sl_total", nullable = false)
    private BigDecimal slTotal;
    @Column(name = "sl_used", nullable = false)
    private BigDecimal slUsed;
}
