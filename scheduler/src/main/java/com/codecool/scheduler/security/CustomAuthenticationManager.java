package com.codecool.scheduler.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationManager implements AuthenticationManager {

    private final EmployeeDetailsService employeeDetailsService;

    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationManager(EmployeeDetailsService employeeDetailsService,
                                       PasswordEncoder passwordEncoder) {
        this.employeeDetailsService = employeeDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Authentication auth = null;
        UserDetails employee = employeeDetailsService.loadUserByUsername(name);
        if (passwordEncoder.matches(password, employee.getPassword())) {
            auth = new UsernamePasswordAuthenticationToken(
                    name, password, employee.getAuthorities());
        }
        return auth;
    }
}
