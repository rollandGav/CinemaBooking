package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.UserDto;
import com.example.CinemaBooking.model.entities.User;
import com.example.CinemaBooking.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // register (public)
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto dto) {
        User u = new User();
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setRole(dto.getRole() != null ? dto.getRole() : com.example.CinemaBooking.model.enums.Role.ROLE_USER);
        userRepo.save(u);
        u.setPassword(null);
        return ResponseEntity.ok(u);
    }

    // get current user (requires auth)
    @GetMapping("/me")
    public ResponseEntity<User> me(java.security.Principal principal) {
        User u = userRepo.findByEmail(principal.getName()).orElseThrow();
        u.setPassword(null);
        return ResponseEntity.ok(u);
    }
}