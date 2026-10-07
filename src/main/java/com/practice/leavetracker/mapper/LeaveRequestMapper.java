package com.practice.leavetracker.mapper;

import com.practice.leavetracker.dto.LeaveRequestDto;
import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.entity.LeaveRequest;
import org.springframework.stereotype.Component;

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
                leaveRequest.getAppliedAt()
        );
    }

    public LeaveRequest toEntity(LeaveRequestDto leaveRequestDto, Employee employee) {
        return new LeaveRequest(
                leaveRequestDto.id(),
                employee,
                leaveRequestDto.leaveType(),
                leaveRequestDto.startDate(),
                leaveRequestDto.endDate(),
                leaveRequestDto.duration(),
                leaveRequestDto.reason(),
                leaveRequestDto.status(),
                leaveRequestDto.appliedAt()
        );
    }
}
