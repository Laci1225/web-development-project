package com.example.webdevelopmentproject.controller;

import com.example.webdevelopmentproject.service.UserService;
import com.example.webdevelopmentproject.user.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        Cookie[] cookies = request.getCookies();
        String jwtToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }
        return ResponseEntity.ok(userService.getMyData(jwtToken));
    }
}
