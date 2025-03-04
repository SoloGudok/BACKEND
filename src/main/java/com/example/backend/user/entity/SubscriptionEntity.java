package com.example.backend.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Subscription")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscription_id;
    @CreationTimestamp
    private Timestamp created_at;
    private Timestamp deleted_at;
    private int status;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity user;

}
