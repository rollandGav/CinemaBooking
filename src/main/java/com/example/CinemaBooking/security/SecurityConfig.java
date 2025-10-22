package com.example.CinemaBooking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
// public swagger
                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
// public read-only endpoints
                                .requestMatchers(HttpMethod.GET, "/api/movies/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/screenings/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/rooms/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/seats/**").permitAll()
// registration public
                                .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
// bookings and users require authentication
                                .requestMatchers("/api/bookings/**").authenticated()
                                .requestMatchers("/api/users/me").authenticated()
// admin-only endpoints (method-level security as extra) - still keep here
                                .requestMatchers(HttpMethod.POST, "/api/movies/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/movies/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/movies/**").hasRole("ADMIN")
// fallback: authenticate
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
