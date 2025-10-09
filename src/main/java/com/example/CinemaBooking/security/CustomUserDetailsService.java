package com.example.CinemaBooking.security;

import com.example.CinemaBooking.model.entities.User;
import com.example.CinemaBooking.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        GrantedAuthority auth = new SimpleGrantedAuthority(u.getRole().name());
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), Collections.singleton(auth));
    }
}
