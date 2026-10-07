package com.practice.leavetracker.service.impl;

import com.practice.leavetracker.dto.LeaveRequestDto;
import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.entity.LeaveRequest;
import com.practice.leavetracker.exception.ResourceNotFoundException;
import com.practice.leavetracker.mapper.LeaveRequestMapper;
import com.practice.leavetracker.repository.EmployeeRepository;
import com.practice.leavetracker.repository.LeaveRequestRepository;
import com.practice.leavetracker.service.LeaveRequestService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor

public class LeaveRequestServiceImpl implements LeaveRequestService {


    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveRequestMapper leaveRequestMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public LeaveRequestDto createLeaveRequest(LeaveRequestDto leaveRequestDto) {
        Employee employee = employeeRepository.findById(leaveRequestDto.empId()).orElseThrow(()->new ResourceNotFoundException("Employee not found"));
        validateProbation(employee,leaveRequestDto.startDate());
        LeaveRequest leaveRequest = leaveRequestMapper.toEntity(leaveRequestDto,employee);
        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);
        return leaveRequestMapper.toDto(savedLeaveRequest);
    }


    @Override
    public List<LeaveRequestDto> getAllLeaveRequests() {
        return leaveRequestRepository.findAll().stream().map(leaveRequestMapper::toDto).toList();
    }

    @Override
    public LeaveRequestDto getLeaveRequestById(long id) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("LeaveRequest not found"));

        return leaveRequestMapper.toDto(leaveRequest);
    }
    private void validateProbation(Employee employee, LocalDate startDate) {
        if(startDate.isBefore(employee.getProbationEndDate().plusDays(1))){
            throw new RuntimeException("Leave cannot be taken before probation ends");
        }
    }

}
