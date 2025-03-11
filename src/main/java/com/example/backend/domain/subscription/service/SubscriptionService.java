package com.example.backend.domain.subscription.service;

import com.example.backend.domain.subscription.dto.SubscriptionRes;
import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.domain.subscription.entity.Subscription;
import com.example.backend.domain.subscription.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    // 카테고리 ID로 구독 리스트 조회
    public List<SubscriptionRes> getSubscriptionsByCategoryId(Long categoryId) {
        return subscriptionRepository.findByCategoryId(categoryId).stream()
                .map(subscription -> SubscriptionRes.builder()
                        .id(subscription.getId())
                        .name(subscription.getName())
                        .price(subscription.getPrice())
                        .content(subscription.getContent())
                        .homepage(subscription.getHomepage())
                        .build())
                .collect(Collectors.toList());
    }
}
