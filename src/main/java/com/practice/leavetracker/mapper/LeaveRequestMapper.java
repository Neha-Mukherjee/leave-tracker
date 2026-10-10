package com.practice.leavetracker.mapper;

import com.practice.leavetracker.dto.LeaveRequestDto;
import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.entity.LeaveRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LeaveRequestMapper {

    public LeaveRequestDto toDto(LeaveRequest leaveRequest) {
        return new LeaveRequestDto(
                leaveRequest.getId(),
                leaveRequest.getEmployee().getEmpId(),
                leaveRequest.getLeaveType(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getDuration(),
                leaveRequest.getReason(),
                leaveRequest.getStatus(),
                leaveRequest.getAppliedAt(),
                leaveRequest.getMedicalDocumentName(),
                leaveRequest.getMedicalDocumentPath()
        );
    }

    public LeaveRequest toEntity(LeaveRequestDto leaveRequestDto, Employee employee, BigDecimal duration, String medicalDocumentName, String medicalDocumentPath) {
        return new LeaveRequest(
                leaveRequestDto.id(),
                employee,
                leaveRequestDto.leaveType(),
                leaveRequestDto.startDate(),
                leaveRequestDto.endDate(),
                duration,
                leaveRequestDto.reason(),
                leaveRequestDto.status(),
                leaveRequestDto.appliedAt(),
                medicalDocumentName,
                medicalDocumentPath
        );
    }
}
