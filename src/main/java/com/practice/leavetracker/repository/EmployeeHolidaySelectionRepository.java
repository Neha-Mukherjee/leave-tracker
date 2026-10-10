package com.practice.leavetracker.repository;

import com.practice.leavetracker.dto.EmployeeHolidaySelectionDto;
import com.practice.leavetracker.entity.EmployeeHolidaySelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeHolidaySelectionRepository extends JpaRepository<EmployeeHolidaySelection, Long> {
    @Query("""
        SELECT s
        FROM EmployeeHolidaySelection s
        JOIN FETCH s.employee e
        JOIN FETCH s.companyHoliday h
        WHERE e.empId = :empId
          AND h.holidayDate BETWEEN :startDate AND :endDate
        ORDER BY h.holidayDate
    """)
    List<EmployeeHolidaySelection> findSelectionsForYear(
            @Param("empId") Long empId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
