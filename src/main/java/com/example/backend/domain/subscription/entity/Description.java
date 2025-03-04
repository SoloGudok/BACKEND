package com.example.backend.domain.subscription.entity;

import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "description")
public class Description extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint")
    private Long id;

    @Column(name = "account_email", nullable = false, columnDefinition = "VARCHAR(255)")
    private String accountEmail;

    @Column(name = "account_password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String accountPassword;

    @Column(name = "approval", nullable = false, columnDefinition = "BOOLEAN")
    private boolean approval;

}
