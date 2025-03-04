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
@Table(name = "Detail")
public class DetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detail_id;
    @CreationTimestamp
    private Timestamp created_at;
    private boolean combination;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="subscription_id")
    private SubscriptionEntity subscription;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="service_id")
    private ServiceEntity service;
}
