package com.example.webdevelopmentproject.controller;

import com.example.webdevelopmentproject.service.UserService;
import com.example.webdevelopmentproject.persistence.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.webdevelopmentproject.config.GetJwtToken.extractToken;

@RestController
@RequestMapping("v1/")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("hello/getAllUser")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("private/getMyData")
    public ResponseEntity<User> getMyData(HttpServletRequest request) {
        var jwtToken = extractToken(request);
        return ResponseEntity.ok(userService.getMyData(jwtToken));
    }
}
