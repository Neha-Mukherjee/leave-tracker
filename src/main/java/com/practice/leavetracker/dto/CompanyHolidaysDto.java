package com.practice.leavetracker.dto;

import java.time.LocalDate;

public record CompanyHolidaysDto (Long id, String holidayName, LocalDate holidayDate){
}
