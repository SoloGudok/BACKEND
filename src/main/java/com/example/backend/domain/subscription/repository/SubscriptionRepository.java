package com.example.backend.domain.subscription.repository;

import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.domain.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByCategoryId(Long categoryId);
}
