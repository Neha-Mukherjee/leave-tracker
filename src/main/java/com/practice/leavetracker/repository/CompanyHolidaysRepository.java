package com.practice.leavetracker.repository;

import com.practice.leavetracker.entity.CompanyHolidays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyHolidaysRepository extends JpaRepository<CompanyHolidays, Long> {
}
