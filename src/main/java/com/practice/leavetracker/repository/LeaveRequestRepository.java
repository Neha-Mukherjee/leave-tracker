package com.practice.leavetracker.repository;

import com.practice.leavetracker.dto.LeaveRequestDto;
import com.practice.leavetracker.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatus(String status);
}
