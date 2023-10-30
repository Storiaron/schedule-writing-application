package com.codecool.scheduler.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtil {
    private final String SECRET_KEY = System.getenv("SECRET_KEY");

    private EmployeeDetailsService employeeDetailsService;

    public TokenUtil(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    public UserDetails parseToken(String token) {
        String splitToken = token.substring(7);

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());

            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT jwt = verifier.verify(splitToken);

            String clientName = jwt.getSubject();
            return employeeDetailsService.loadUserByUsername(clientName);

        } catch (Exception e) {
            return null;
        }
    }
}
