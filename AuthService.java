package com.devteamup.service;

import com.devteamup.model.User;
import com.devteamup.repository.UserRepository;
import com.devteamup.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.DEV);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return jwtUtil.generateToken(user.getUsername(), user.getRole().name()); // Convert enum to String
    }

    public String login(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return jwtUtil.generateToken(user.getUsername(), existingUser.getRole().name()); // Convert enum to String
        }
        throw new RuntimeException("Invalid credentials");
    }
}