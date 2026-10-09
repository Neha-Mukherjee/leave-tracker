package com.practice.leavetracker.repository;

import com.practice.leavetracker.entity.EmployeeWeeklyOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeWeeklyoffRepository extends JpaRepository<EmployeeWeeklyOff , Long> {
    List<EmployeeWeeklyOff> findByEmployeeEmpIdOrderByWeekStartDateAsc(Long empId);

    boolean existsByEmployeeEmpIdAndWeekStartDate(Long empId, LocalDate weekStartDate);

    @Modifying
    @Query("""
    DELETE FROM EmployeeWeeklyOff w
    WHERE w.employee.empId = :empId
      AND w.weekStartDate = :weekStartDate
""")
    void deleteByEmployeeEmpIdAndWeekStartDate(@Param("empId") Long empId, @Param("weekStartDate") LocalDate weekStartDate
    );
}

