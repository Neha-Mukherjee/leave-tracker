package com.practice.leavetracker.repository;

import com.practice.leavetracker.entity.EmployeeWeeklyOff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeWeeklyoffRepository extends JpaRepository<EmployeeWeeklyOff , Long> {
    List<EmployeeWeeklyOff> findByEmployeeEmpIdOrderByWeekStartDateAsc(Long empId);

    boolean existsByEmployeeEmpIdAndWeekStartDate(Long empId, LocalDate weekStartDate);

    void deleteByEmployeeEmpIdAndWeekStartDate(Long empId, LocalDate weekStartDate);
}

