package com.example.backend.user.entity;

import com.example.backend.subscription.entity.ServiceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Expenditure")
public class ExpenditureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenditure_id;
    @CreationTimestamp
    private Timestamp created_at;
    private String content;
    private int amount;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="service_id")
    private ServiceEntity service;

}
