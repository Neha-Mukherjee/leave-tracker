package com.practice.leavetracker.service;

import com.practice.leavetracker.dto.LeaveRequestDto;
import com.practice.leavetracker.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {

    LeaveRequestDto createLeaveRequest(LeaveRequestDto leaveRequestDto, MultipartFile medicalDocument);

    List<LeaveRequestDto> getAllLeaveRequests();

    LeaveRequestDto getLeaveRequestById(long id);

    LeaveRequestDto updateLeaveRequest(Long id, String status);

    


}
