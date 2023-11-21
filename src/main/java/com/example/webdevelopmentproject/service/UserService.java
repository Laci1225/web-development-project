package com.example.webdevelopmentproject.service;

import com.example.webdevelopmentproject.config.JwtService;
import com.example.webdevelopmentproject.mapper.UserMapper;
import com.example.webdevelopmentproject.model.UserDto;
import com.example.webdevelopmentproject.persistence.repository.OrderRepository;
import com.example.webdevelopmentproject.persistence.repository.UserRepository;
import com.example.webdevelopmentproject.persistence.entity.User;
import lombok.AllArgsConstructor;
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

    public User getMyData(String token) {
        String userData = jwtService.extractUsername(token);
        return userRepository.findByUsername(userData).orElseThrow();
    }
}
