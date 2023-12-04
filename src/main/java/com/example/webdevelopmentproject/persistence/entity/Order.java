package com.example.webdevelopmentproject.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "_order")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String amount;
    private String weight;
    private Integer userId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}