package com.practice.leavetracker.controller;


import com.practice.leavetracker.dto.LeaveRequestDto;
import com.practice.leavetracker.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leave-tracker/leave-requests")

public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;
    private final ObjectMapper objectMapper;


    //create leave requests
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LeaveRequestDto> createLeaveRequest(
            @RequestPart("leaveRequest") String leaveRequestJson,
            @RequestPart(value = "medicalDocument", required = false)
            MultipartFile medicalDocument) throws Exception {

        LeaveRequestDto leaveRequestDto =
                objectMapper.readValue(
                        leaveRequestJson,
                        LeaveRequestDto.class);

        LeaveRequestDto savedLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequestDto, medicalDocument);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedLeaveRequest);
    }


    //get all leave request
    @GetMapping
    public ResponseEntity<List<LeaveRequestDto>> getAllLeaveRequests(){

        List<LeaveRequestDto> leaveRequests = leaveRequestService.getAllLeaveRequests();

       return ResponseEntity.ok(leaveRequests);
    }
    //get leave request by id

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> getLeaveRequestById(@PathVariable("id") Long id){
        return ResponseEntity.ok(leaveRequestService.getLeaveRequestById(id));
    }

    //update method for status of a leave request
    @PatchMapping("/{id}/status")
    public ResponseEntity<LeaveRequestDto> updateLeaveRequest(@PathVariable ("id")Long id, @RequestParam String status){
        LeaveRequestDto leaveRequestDto = leaveRequestService.updateLeaveRequest(id, status);
        return ResponseEntity.ok(leaveRequestDto);
    }


}
