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
    CommandLineRunner initUser(UserRepository userRepository, PasswordEncoder encoder){
        return args -> {
            if(userRepository.findByEmail("admin@gmail.com").isEmpty()){
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.ROLE_ADMIN);
                userRepository.save(admin);
            }
            if(userRepository.findByEmail("user@gmail.com").isEmpty()){
                User user = new User();
                user.setName("User");
                user.setEmail("user@gmail.com");
                user.setPassword(encoder.encode("user123"));
                user.setRole(Role.ROLE_USER);
                userRepository.save(user);
            }
        };
    }

}
