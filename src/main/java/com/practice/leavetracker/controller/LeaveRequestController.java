package com.practice.leavetracker.controller;


import com.practice.leavetracker.dto.LeaveRequestDto;
import com.practice.leavetracker.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leave-tracker/leave-requests")

public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;


    //create leave requests
    @PostMapping
    public ResponseEntity<LeaveRequestDto> createLeaveRequest(@RequestBody LeaveRequestDto leaveRequestdto){
        LeaveRequestDto leaveRequestDto1 = leaveRequestService.createLeaveRequest(leaveRequestdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(leaveRequestDto1);
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
}
