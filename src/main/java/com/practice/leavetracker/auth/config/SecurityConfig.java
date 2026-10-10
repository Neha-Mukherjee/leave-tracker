package com.practice.leavetracker.auth.config;

import com.practice.leavetracker.auth.service.GoogleOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final GoogleOAuth2UserService googleOAuth2UserService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/leave-tracker/employees/**",
                                "/api/leave-tracker/leave-requests/**","/error",
                                "/api/leave-tracker/company-holidays","/api/leave-tracker/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                .userInfoEndpoint(userInfo -> userInfo.userService(googleOAuth2UserService)
                ));


        return http.build();
    }
}

