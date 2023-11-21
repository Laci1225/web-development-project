package com.example.webdevelopmentproject.model;

import com.example.webdevelopmentproject.persistence.entity.Order;
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
}
