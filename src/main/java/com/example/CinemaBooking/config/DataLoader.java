package com.example.CinemaBooking.config;

import com.example.CinemaBooking.model.entities.User;
import com.example.CinemaBooking.model.enums.Role;
import com.example.CinemaBooking.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initUsers(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (userRepo.findByEmail("admin@example.com").isEmpty()) {
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.ROLE_ADMIN);
                userRepo.save(admin);
            }
            if (userRepo.findByEmail("user@example.com").isEmpty()) {
                User user = new User();
                user.setName("User");
                user.setEmail("user@example.com");
                user.setPassword(encoder.encode("user123"));
                user.setRole(Role.ROLE_USER);
                userRepo.save(user);
            }
        };
    }
}