package com.codecool.scheduler.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class BearerTokenAuthenticatingFilter extends OncePerRequestFilter {

    private final String SECRET_KEY = System.getenv("SECRET_KEY");

    private final TokenUtil tokenUtil;

    public BearerTokenAuthenticatingFilter(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails employee = tokenUtil.parseToken(token);
        if (employee == null) {
            throw new BadCredentialsException("invalid token found in Authorization header");
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                "UserRegistrationApi", "", employee.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
