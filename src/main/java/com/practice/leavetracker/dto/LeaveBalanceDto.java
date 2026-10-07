package com.practice.leavetracker.dto;

public record LeaveBalanceDto(
        Long id, Long empId, Integer year, java.math.BigDecimal plTotal, java.math.BigDecimal plUsed,
        java.math.BigDecimal clTotal, java.math.BigDecimal clUsed, java.math.BigDecimal slTotal,
        java.math.BigDecimal slUsed) {
}
