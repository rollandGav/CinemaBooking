package com.example.CinemaBooking.service.implementation;

import com.example.CinemaBooking.model.dto.UserDto;
import com.example.CinemaBooking.model.entities.User;
import com.example.CinemaBooking.repo.UserRepository;
import com.example.CinemaBooking.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepo;
    public UserServiceImplementation(UserRepository userRepo){ this.userRepo = userRepo; }

    @Override
    public User createUser(UserDto dto){
        User u = new User(); u.setName(dto.getName()); u.setEmail(dto.getEmail());
        return userRepo.save(u);
    }

    @Override
    public User updateUser(Long id, UserDto dto){
        User u = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        u.setName(dto.getName()); u.setEmail(dto.getEmail());
        return userRepo.save(u);
    }

    @Override public Optional<User> findById(Long id){ return userRepo.findById(id); }
    @Override public List<User> findAll(){ return userRepo.findAll(); }
    @Override public void delete(Long id){ userRepo.deleteById(id); }
}
