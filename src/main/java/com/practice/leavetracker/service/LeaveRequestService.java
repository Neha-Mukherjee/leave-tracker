package com.practice.leavetracker.service;

import com.practice.leavetracker.dto.LeaveRequestDto;
import com.practice.leavetracker.entity.Employee;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {

    LeaveRequestDto createLeaveRequest(LeaveRequestDto leaveRequestDto);

    List<LeaveRequestDto> getAllLeaveRequests();

    LeaveRequestDto getLeaveRequestById(long id);


}
