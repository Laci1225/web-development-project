package com.example.webdevelopmentproject.controller;

import com.example.webdevelopmentproject.persistence.entity.Order;
import com.example.webdevelopmentproject.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.webdevelopmentproject.config.GetJwtToken.extractToken;

@RestController
@RequestMapping("v1/private")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @GetMapping("getAllOrderByUserId")
    public ResponseEntity<List<Order>> getAllOrderByUserId(HttpServletRequest request) {
        var jwtToken = extractToken(request);
        return ResponseEntity.ok(orderService.gerAllOrderByUserId(jwtToken));
    }

    @PostMapping("create")
    public ResponseEntity<Order> createOrder(HttpServletRequest request, @RequestBody Order order) {
        var jwtToken = extractToken(request);
        return ResponseEntity.ok(orderService.createOrder(jwtToken, order));
    }

}
