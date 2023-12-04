package com.example.webdevelopmentproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class OrderDto {
    private Integer id;
    private String name;
    private String amount;
    private String weight;
    private Integer userId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
