package com.example.CinemaBooking.controller;


import com.example.CinemaBooking.model.dto.UserDto;
import com.example.CinemaBooking.model.entities.User;
import com.example.CinemaBooking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    @Operation(summary = "Create user")
    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<User>> all() { return ResponseEntity.ok(userService.findAll()); }

    @Operation(summary = "Update user")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDto dto) { return ResponseEntity.ok(userService.updateUser(id, dto)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { userService.delete(id); return ResponseEntity.noContent().build(); }
}