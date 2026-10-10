package com.practice.leavetracker.service.impl;

import com.practice.leavetracker.dto.LeaveRequestDto;
import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.entity.LeaveRequest;
import com.practice.leavetracker.exception.ResourceNotFoundException;
import com.practice.leavetracker.mapper.LeaveRequestMapper;
import com.practice.leavetracker.repository.EmployeeRepository;
import com.practice.leavetracker.repository.LeaveRequestRepository;
import com.practice.leavetracker.service.EmailService;
import com.practice.leavetracker.service.LeaveBalanceService;
import com.practice.leavetracker.service.LeaveRequestService;
import com.practice.leavetracker.service.MedicalDocumentService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Transactional
public class LeaveRequestServiceImpl implements LeaveRequestService {


    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveRequestMapper leaveRequestMapper;
    private final EmployeeRepository employeeRepository;
    private final MedicalDocumentService medicalDocumentService;
    private final LeaveBalanceService leaveBalanceService;
    private final EmailService emailService;

    @Override
    public LeaveRequestDto createLeaveRequest(LeaveRequestDto leaveRequestDto,MultipartFile medicalDocument) {
        Employee employee = employeeRepository.findById(leaveRequestDto.empId()).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        validateProbation(employee, leaveRequestDto.startDate());

        //validate that start date should not be after end date
        if (leaveRequestDto.startDate().isAfter(leaveRequestDto.endDate())) {
            throw new RuntimeException("Start date cannot be after end date");
        }

        //validate leave type
        if (!leaveRequestDto.leaveType().equals("PL")
                && !leaveRequestDto.leaveType().equals("CL")
                && !leaveRequestDto.leaveType().equals("SL")) {

            throw new RuntimeException("Invalid leave type");
        }

        // validate backdated leave
        if (leaveRequestDto.startDate().isBefore(LocalDate.now())
                && !leaveRequestDto.leaveType().equals("SL")) {

            throw new RuntimeException(
                    "Backdated leave is allowed only for SL"
            );
        }

        //calculate duration
        long days = ChronoUnit.DAYS.between(leaveRequestDto.startDate(), leaveRequestDto.endDate()) + 1;
        BigDecimal duration = BigDecimal.valueOf(days);

        leaveBalanceService.validateLeaveBalance(employee.getEmpId(), leaveRequestDto.leaveType(),duration);


        //validate medical doc
        validateMedicalDocument(leaveRequestDto, duration, medicalDocument);

        String medicalDocumentName = null;
        String medicalDocumentPath = null;
        // Save document if provided
        if (medicalDocument != null
                && !medicalDocument.isEmpty()) {
            medicalDocumentName = medicalDocument.getOriginalFilename();

            medicalDocumentPath =medicalDocumentService.saveDocument(medicalDocument);
        }
        LeaveRequest leaveRequest = leaveRequestMapper.toEntity(leaveRequestDto, employee, duration,medicalDocumentName,medicalDocumentPath);
        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);
        return leaveRequestMapper.toDto(savedLeaveRequest);


    }


    @Override
    public List<LeaveRequestDto> getAllLeaveRequests(String status) {
        if(status == null || status.isBlank()){
            return leaveRequestRepository.findAll().stream().map(leaveRequestMapper::toDto).toList();

        }

        return leaveRequestRepository.findByStatus(status).stream().map(leaveRequestMapper::toDto).toList();
    }

    @Override
    public LeaveRequestDto getLeaveRequestById(long id) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("LeaveRequest not found"));

        return leaveRequestMapper.toDto(leaveRequest);
    }

    @Override
    public LeaveRequestDto updateLeaveRequest(Long id, String status) {
        LeaveRequest leaveRequest=leaveRequestRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("LeaveRequest not found"));
        if(!leaveRequest.getStatus().equals("PENDING")){
            throw new ResourceNotFoundException("Leave Request cannot be updated");
        }
        if (!status.equals("APPROVED") && !status.equals("REJECTED")) {
            throw new RuntimeException("Invalid status");
        }
        if("APPROVED".equals(status)){
            leaveBalanceService.deductLeaveBalance(leaveRequest.getEmployee().getEmpId(), leaveRequest.getLeaveType(),leaveRequest.getDuration());
        }

        leaveRequest.setStatus(status);
        LeaveRequest updatedLeaveRequest=leaveRequestRepository.save(leaveRequest);

        emailService.sendLeaveStatusEmail(updatedLeaveRequest);
        return leaveRequestMapper.toDto(updatedLeaveRequest);
    }

    private void validateProbation(Employee employee, LocalDate startDate) {
        if(startDate.isBefore(employee.getProbationEndDate().plusDays(1))){
            throw new RuntimeException("Leave cannot be taken before probation ends");
        }
    }

    private void validateMedicalDocument(
            LeaveRequestDto leaveRequestDto,BigDecimal duration,MultipartFile medicalDocument) {

        if (!leaveRequestDto.leaveType().equals("SL")) {
            return;
        }

        if (duration.compareTo(BigDecimal.ONE) > 0
                && (medicalDocument == null || medicalDocument.isEmpty())) {

            throw new RuntimeException("Medical document is required for SL greater than 1 day");
        }

        if (medicalDocument == null || medicalDocument.isEmpty()) {
            return;
        }

        if (medicalDocument.getSize() > 5 * 1024 * 1024) {
            throw new RuntimeException("Medical document must not exceed 5 MB");
        }

        String contentType = medicalDocument.getContentType();

        if (!"application/pdf".equals(contentType)
                && !"image/jpeg".equals(contentType)
                && !"image/png".equals(contentType)) {

            throw new RuntimeException(
                    "Only PDF, JPEG and PNG files are allowed");
        }
    }

}
