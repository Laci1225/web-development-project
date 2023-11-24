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
@RequestMapping("v1/private/order")
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

    @PatchMapping(path = "update/{orderId}")
    public ResponseEntity<Order> updateOrder(HttpServletRequest request, @RequestBody Order order, @PathVariable("orderId") Integer id) {
        var jwtToken = extractToken(request);
        return ResponseEntity.ok(orderService.updateOrder(jwtToken, order, id));
    }

    @DeleteMapping(path = "delete/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") Integer id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }

}
