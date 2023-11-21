package com.example.webdevelopmentproject.service;

import com.example.webdevelopmentproject.repository.UserRepository;
import com.example.webdevelopmentproject.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
