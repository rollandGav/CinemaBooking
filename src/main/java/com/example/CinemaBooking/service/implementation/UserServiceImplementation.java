package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.dto.UserDto;
import com.example.CinemaBooking.model.entities.User;
import com.example.CinemaBooking.repo.UserRepository;
import com.example.CinemaBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User createUser(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return repository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User updateUser(Long id, UserDto dto) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
