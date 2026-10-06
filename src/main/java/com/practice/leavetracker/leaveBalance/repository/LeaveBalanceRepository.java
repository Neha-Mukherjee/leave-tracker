package com.practice.leavetracker.leaveBalance.repository;

import com.practice.leavetracker.leaveBalance.entity.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
    Optional<LeaveBalance> findByEmployeeEmpIdAndYear(Long empId, Integer year);
}
