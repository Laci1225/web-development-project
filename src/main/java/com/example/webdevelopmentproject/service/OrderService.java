package com.example.webdevelopmentproject.service;

import com.example.webdevelopmentproject.config.JwtService;
import com.example.webdevelopmentproject.persistence.entity.Order;
import com.example.webdevelopmentproject.persistence.repository.OrderRepository;
import com.example.webdevelopmentproject.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private JwtService jwtService;

    public List<Order> gerAllOrderByUserId(String token) {
        String username = jwtService.extractUsername(token);
        var user = userRepository.findByUsername(username).orElseThrow();
        return orderRepository.findAllByUserId(user.getId()).orElseThrow();
    }

    public Order createOrder(String token, Order order) {
        String username = jwtService.extractUsername(token);
        var user = userRepository.findByUsername(username).orElseThrow();
        order.setUserId(user.getId());
        orderRepository.save(order);
        return order;
    }
}
