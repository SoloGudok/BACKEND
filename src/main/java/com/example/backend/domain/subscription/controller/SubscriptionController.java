package com.example.backend.domain.subscription.controller;

import com.example.backend.domain.subscription.dto.CombinationSubscriptionResponseDto;
import com.example.backend.domain.subscription.dto.SubscriptionRes;
import com.example.backend.domain.subscription.dto.SubscriptionResponseDto;
import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.domain.subscription.entity.Subscription;
import com.example.backend.domain.subscription.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscription")
@Tag(name = "Subscription", description = "구독 관련 api입니다.")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // 카테고리별 구독 리스트 조회
    @GetMapping("/category/{categoryId}")
    public List<SubscriptionRes> getSubscriptionsByCategoryId(@PathVariable Long categoryId) {
        return subscriptionService.getSubscriptionsByCategoryId(categoryId);
    }

    @GetMapping("/individual")
    public ResponseEntity<List<SubscriptionResponseDto>> getIndividualSubscriptions(@RequestParam Long userId) {
        return ResponseEntity.ok(subscriptionService.getIndividualSubscriptions(userId));
    }

    @GetMapping("/combination")
    public ResponseEntity<List<CombinationSubscriptionResponseDto>> getCombinationSubscriptions(@RequestParam Long userId) {
        return ResponseEntity.ok(subscriptionService.getCombinationSubscriptions(userId));
    }

}
