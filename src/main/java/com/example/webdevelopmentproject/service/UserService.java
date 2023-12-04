package com.example.webdevelopmentproject.service;

import com.example.webdevelopmentproject.config.JwtService;
import com.example.webdevelopmentproject.mapper.UserMapper;
import com.example.webdevelopmentproject.model.UserDto;
import com.example.webdevelopmentproject.persistence.entity.Role;
import com.example.webdevelopmentproject.persistence.repository.OrderRepository;
import com.example.webdevelopmentproject.persistence.repository.UserRepository;
import com.example.webdevelopmentproject.persistence.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private OrderRepository orderRepository;
    private JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUser() {
        var users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = userMapper.fromUserToUserDto(user);
            var orders = orderRepository.findAllByUserId(user.getId()).orElseThrow();
            userDto.setOrders(orders);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    public UserDto getMyData(String token) {
        String userData = jwtService.extractUsername(token);
        var user = userRepository.findByUsername(userData).orElseThrow();
        return userMapper.fromUserToUserDto(user);
    }

    public UserDto deleteUser(Integer id, String token) {
        String adminData = jwtService.extractUsername(token);
        if (userRepository.findByUsername(adminData).orElseThrow().getRole().equals(Role.ADMIN)) {
            var userDto = userRepository.findById(id).orElseThrow();
            userRepository.deleteById(id);
            return userMapper.fromUserToUserDto(userDto);
        } else return null;
    }

    public UserDto getUserData(Integer id, String token) {
        String adminData = jwtService.extractUsername(token);
        if (userRepository.findByUsername(adminData).orElseThrow().getRole().equals(Role.ADMIN)) {
            var userDto = userRepository.findById(id).orElseThrow();
            return userMapper.fromUserToUserDto(userDto);
        } else return null;
    }

    public UserDto updateUser(String token, User user, String name) {
        String adminData = jwtService.extractUsername(token);
        if (userRepository.findByUsername(adminData).orElseThrow().getRole().equals(Role.ADMIN)) {
            var userInDB = userRepository.findByUsername(name).orElseThrow();
            userInDB.setUsername(user.getUsername());
            userInDB.setEmail(user.getEmail());
            userInDB.setPassword(passwordEncoder.encode(user.getPassword()));
            var savedUser = userRepository.save(userInDB);
            return userMapper.fromUserToUserDto(savedUser);
        } else return null;
    }
}
