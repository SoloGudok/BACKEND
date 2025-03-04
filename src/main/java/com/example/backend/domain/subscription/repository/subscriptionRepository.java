package com.example.backend.domain.subscription.repository;

import com.example.backend.domain.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface subscriptionRepository extends JpaRepository<Subscription, Long> {
}
