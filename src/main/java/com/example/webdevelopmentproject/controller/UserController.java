package com.example.webdevelopmentproject.controller;

import com.example.webdevelopmentproject.model.UserDto;
import com.example.webdevelopmentproject.service.UserService;
import com.example.webdevelopmentproject.persistence.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.webdevelopmentproject.config.GetJwtToken.extractToken;

@RestController
@RequestMapping("v1/")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("hello/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("private/getMyData")
    public ResponseEntity<UserDto> getMyData(HttpServletRequest request) {
        var jwtToken = extractToken(request);
        return ResponseEntity.ok(userService.getMyData(jwtToken));
    }

    @DeleteMapping("private/delete/{id}")
    public ResponseEntity<UserDto> deleteUser(HttpServletRequest request, @PathVariable("id") Integer id) {
        var jwtToken = extractToken(request);
        var response = userService.deleteUser(id, jwtToken);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("private/getUserData/{id}")
    public ResponseEntity<UserDto> getUserData(HttpServletRequest request, @PathVariable("id") Integer id) {
        var jwtToken = extractToken(request);
        var response = userService.getUserData(id, jwtToken);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "private/update/{username}")
    public ResponseEntity<UserDto> updateOrder(HttpServletRequest request, @RequestBody User user, @PathVariable("username") String name) {
        var jwtToken = extractToken(request);
        return ResponseEntity.ok(userService.updateUser(jwtToken, user, name));
    }
}
