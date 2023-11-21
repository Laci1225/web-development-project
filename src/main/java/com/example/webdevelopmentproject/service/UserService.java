package com.example.webdevelopmentproject.service;

import com.example.webdevelopmentproject.config.JwtService;
import com.example.webdevelopmentproject.persistence.repository.UserRepository;
import com.example.webdevelopmentproject.persistence.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private JwtService jwtService;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getMyData(String token) {
        String userData = jwtService.extractUsername(token);
        return userRepository.findByUsername(userData).orElseThrow();
    }
}
