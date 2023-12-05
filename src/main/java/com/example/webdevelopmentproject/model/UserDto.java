package com.example.webdevelopmentproject.model;

import com.example.webdevelopmentproject.persistence.entity.Order;
import com.example.webdevelopmentproject.persistence.entity.Role;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private List<Order> orders;
    @Enumerated
    private Role role;
}
