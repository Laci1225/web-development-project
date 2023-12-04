package com.example.webdevelopmentproject.service;

import com.example.webdevelopmentproject.config.JwtService;
import com.example.webdevelopmentproject.persistence.entity.Order;
import com.example.webdevelopmentproject.persistence.repository.OrderRepository;
import com.example.webdevelopmentproject.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private JwtService jwtService;

    public List<Order> gerAllMyOrders(String token) {
        String username = jwtService.extractUsername(token);
        var user = userRepository.findByUsername(username).orElseThrow();
        return orderRepository.findAllByUserId(user.getId()).orElseThrow();
    }

    public List<Order> gerAllOrderByUserId(Integer id) {
        return orderRepository.findAllByUserId(id).orElseThrow();
    }

    public Order createOrder(Order order, Integer id) {
        var user = userRepository.findById(id).orElseThrow();
        order.setCreatedDate(LocalDateTime.now());
        order.setModifiedDate(LocalDateTime.now());
        order.setUserId(user.getId());
        orderRepository.save(order);
        return order;
    }

    public Order updateOrder(Order order, Integer id, Integer userId) {
        var user = userRepository.findById(userId).orElseThrow();
        order.setModifiedDate(LocalDateTime.now());
        order.setId(id);
        order.setUserId(user.getId());
        orderRepository.save(order);
        return order;
    }

    public Order deleteOrder(Integer id) {
        var order = orderRepository.findById(id).orElseThrow();
        orderRepository.deleteById(id);
        return order;
    }
}
