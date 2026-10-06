package com.practice.leavetracker.auth.service;

import com.practice.leavetracker.employee.entity.Employee;
import com.practice.leavetracker.exception.ResourceNotFoundException;
import com.practice.leavetracker.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleOAuth2UserService extends DefaultOAuth2UserService {
    private final EmployeeRepository employeeRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        OAuth2User user = super.loadUser(userRequest);

        String email = user.getAttribute("email");

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (!employee.isActive()) {
            throw new ResourceNotFoundException("Employee is inactive");
        }

        return user;
    }
}
