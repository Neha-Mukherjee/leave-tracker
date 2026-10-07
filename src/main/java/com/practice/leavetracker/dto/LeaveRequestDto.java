package com.practice.leavetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record LeaveRequestDto(Long id,
                              Long empId,
                              String leaveType,
                              LocalDate startDate,
                              LocalDate endDate,
                              BigDecimal duration,
                              String reason,
                              String status,
                              LocalDateTime appliedAt) {
}
