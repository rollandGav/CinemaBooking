package com.example.CinemaBooking.controller;

import com.example.CinemaBooking.model.dto.UserDto;
import com.example.CinemaBooking.model.entities.User;
import com.example.CinemaBooking.model.enums.Role;
import com.example.CinemaBooking.repo.UserRepository;
import com.example.CinemaBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole() != null ? dto.getRole() : Role.ROLE_USER);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<User> returnMe (java.security.Principal principal){
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}