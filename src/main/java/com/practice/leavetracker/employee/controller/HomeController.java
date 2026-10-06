package com.practice.leavetracker.employee.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Login successful!";
    }

    @GetMapping
    public String getCurrentUser(@AuthenticationPrincipal OAuth2User user) {
        return "logged in as " + user.getAttribute("email");
    }
}