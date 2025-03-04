package com.example.backend.domain.subscription.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name = "subscription_img")
public class SubscriptionImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint")
    private Long id;

    @Column(name = "subscription_img_url")
    private String subscriptionImgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
}
