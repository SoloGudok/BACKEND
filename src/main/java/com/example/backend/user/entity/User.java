package com.example.backend.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String password;
    private String name;
    private Integer gender;
    private LocalDateTime birth;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}