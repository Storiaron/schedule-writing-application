package com.codecool.scheduler.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.codecool.scheduler.dto.LoginDTO;
import com.codecool.scheduler.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StreamUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CustomUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String SECRET_KEY = System.getenv("SECRET_KEY");

    private final AuthenticationManager customAuthenticationManager;
    private final EmployeeRepository employeeRepository;

    public CustomUsernameAndPasswordAuthenticationFilter(AuthenticationManager customAuthenticationManager,
                                                         EmployeeRepository employeeRepository) {
        super(customAuthenticationManager);
        this.customAuthenticationManager = customAuthenticationManager;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response
    ) throws AuthenticationException {
        try {
            byte[] inputStreamBytes = StreamUtils.copyToByteArray(request.getInputStream());
            LoginDTO loginDTO = new ObjectMapper().readValue(inputStreamBytes, LoginDTO.class);
            return customAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.username(),
                            loginDTO.password(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response,
                                         FilterChain chain,
                                         Authentication authResult) throws IOException, ServletException {

        String name = String.valueOf(authResult.getPrincipal());
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        List<String> roles = new ArrayList<>(authorities.size());
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }

        String accessToken = getJwtToken(name, roles);
        response.setHeader("Authorization", "Bearer " + accessToken);
    }

    private String getJwtToken(String name, List<String> roles) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());

        String accessToken = JWT.create()
                .withSubject(name)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim("role", roles)
                .sign(algorithm);
        return accessToken;
    }
}
