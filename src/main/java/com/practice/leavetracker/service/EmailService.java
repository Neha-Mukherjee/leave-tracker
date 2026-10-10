package com.practice.leavetracker.service;

import com.practice.leavetracker.entity.LeaveRequest;

public interface EmailService {

    void sendLeaveStatusEmail(LeaveRequest leaveRequest);
}
