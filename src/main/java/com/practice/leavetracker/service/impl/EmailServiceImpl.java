package com.practice.leavetracker.service.impl;

import com.practice.leavetracker.entity.Employee;
import com.practice.leavetracker.entity.LeaveRequest;
import com.practice.leavetracker.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender javaMailSender;
    @Override
    public void sendLeaveStatusEmail(LeaveRequest leaveRequest) {
        Employee employee = leaveRequest.getEmployee();
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("noreply@leavetrackerlocal");
        message.setTo(employee.getEmail());

        message.setSubject("Leave Request"+leaveRequest.getStatus());

        message.setText("Hello " + employee.getFirstName() + " "
                + employee.getLastName() + ",\n\n"
                + "Your leave request has been "
                + leaveRequest.getStatus().toLowerCase() + ".\n\n"
                + "Leave Type: " + leaveRequest.getLeaveType() + "\n"
                + "Start Date: " + leaveRequest.getStartDate() + "\n"
                + "End Date: " + leaveRequest.getEndDate() + "\n\n"
                + "Regards,\nManager"
        );
        javaMailSender.send(message);
    }
}
