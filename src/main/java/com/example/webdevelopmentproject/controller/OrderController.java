package com.example.webdevelopmentproject.controller;

import com.example.webdevelopmentproject.model.OrderDto;
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

    @GetMapping("getAllMyOrders")
    public ResponseEntity<List<OrderDto>> getAllMyOrders(HttpServletRequest request) {
        var jwtToken = extractToken(request);
        return ResponseEntity.ok(orderService.gerAllMyOrders(jwtToken));
    }

    @GetMapping("getAllOrderByUserId/{userId}")
    public ResponseEntity<List<OrderDto>> getAllOrderByUserId(@PathVariable("userId") Integer id) {
        return ResponseEntity.ok(orderService.gerAllOrderByUserId(id));
    }

    @PostMapping("create/{userId}")
    public ResponseEntity<OrderDto> createOrder(@RequestBody Order order, @PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(orderService.createOrder(order, userId));
    }

    @PatchMapping(path = "update/{orderId}/{userId}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody Order order, @PathVariable("orderId") Integer id, @PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(orderService.updateOrder(order, id, userId));
    }

    @DeleteMapping(path = "delete/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("orderId") Integer id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }

}
