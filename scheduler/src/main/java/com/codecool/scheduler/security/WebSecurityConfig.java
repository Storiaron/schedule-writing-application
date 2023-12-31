package com.codecool.scheduler.security;

import com.codecool.scheduler.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public WebSecurityConfig(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/employee/reset").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/employee").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/employee/role").hasAnyRole("Employee", "Employer")
                        .requestMatchers(HttpMethod.POST, "/api/request").hasAnyRole("Employee", "Employer")
                        .requestMatchers(HttpMethod.POST, "/api/schedule").hasRole("Employer")
                        .requestMatchers(HttpMethod.GET, "/api/schedule/options").hasRole("Employer")
                        .requestMatchers(HttpMethod.GET, "api/employee").hasRole("Employer")
                        .requestMatchers(HttpMethod.POST, "/api/schedule/generate").hasRole("Employer")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterAfter(customUsernameAndPasswordAuthenticationFilter(), ExceptionTranslationFilter.class)
                .addFilterAfter(bearerTokenAuthenticatingFilter(), ExceptionTranslationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return http.build();
    }
    @Bean
    public AuthenticationManager customAuthenticationManager() {
        return new CustomAuthenticationManager(employeeDetailsService(), passwordEncoder());
    }

    @Bean
    public CustomUsernameAndPasswordAuthenticationFilter customUsernameAndPasswordAuthenticationFilter() {
        return new CustomUsernameAndPasswordAuthenticationFilter(customAuthenticationManager(), employeeRepository);
    }

    @Bean
    public EmployeeDetailsService employeeDetailsService() {
        return new EmployeeDetailsService(employeeRepository);
    }

    @Bean
    public BearerTokenAuthenticatingFilter bearerTokenAuthenticatingFilter() {
        return new BearerTokenAuthenticatingFilter(tokenUtil());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new NoPasswordEncoder();
    }

    @Bean
    public TokenUtil tokenUtil() {
        return new TokenUtil(employeeDetailsService());
    }
}
