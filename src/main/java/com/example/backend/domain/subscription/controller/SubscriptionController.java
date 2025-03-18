package com.example.backend.domain.subscription.controller;

import com.example.backend.domain.subscription.dto.CombinationSubscriptionResponseDto;
import com.example.backend.domain.subscription.dto.SubscriptionDTO;
import com.example.backend.domain.subscription.dto.SubscriptionRes;
import com.example.backend.domain.subscription.dto.SubscriptionResponseDto;
import com.example.backend.domain.subscription.service.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000") //
@RestController
@RequestMapping("/api/v1/subscription")
@Tag(name = "Subscription", description = "구독 관련 api입니다.")
@SessionAttributes("selectedSubscriptions")
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

    @GetMapping("/{subscriptionId}") // 🔴 개별 구독 조회 API 추가
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@PathVariable Long subscriptionId) {
        SubscriptionDTO subscription = subscriptionService.getSubscriptionById(subscriptionId);

        if (subscription == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(subscription);
    }

    @CrossOrigin(origins = "http://localhost:3000")  // React 앱의 URL로 설정
    @GetMapping("/category/{categoryId}/dto")
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptionsByCategory(@PathVariable("categoryId") Long categoryId) {
        List<SubscriptionDTO> subscriptions = subscriptionService.getSubscriptionsByCategory(categoryId);
        return ResponseEntity.ok(subscriptions);
    }

    // ✅ 개별 구독 리스트 조회 (개인 구독)
    @GetMapping("/individual")
    public ResponseEntity<List<SubscriptionResponseDto>> getIndividualSubscriptions(@RequestParam Long userId) {
        if (userId == null || userId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        List<SubscriptionResponseDto> subscriptions = subscriptionService.getIndividualSubscriptions(userId);
        return ResponseEntity.ok(subscriptions);
    }

    // ✅ 조합 구독 리스트 조회
    @GetMapping("/combination")
    public ResponseEntity<List<CombinationSubscriptionResponseDto>> getCombinationSubscriptions(@RequestParam Long userId) {
        if (userId == null || userId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        List<CombinationSubscriptionResponseDto> combinationSubscriptions = subscriptionService.getCombinationSubscriptions(userId);
        return ResponseEntity.ok(combinationSubscriptions);
    }

    @PostMapping("/select")
    public ResponseEntity<List<SubscriptionDTO>> selectSubscriptions(@RequestBody List<Long> subscriptionIds) {
        // subscriptionIds 리스트에 포함된 각 ID에 대해 구독 서비스 정보를 조회
        List<SubscriptionDTO> subscriptions = subscriptionIds.stream()
                .map(subscriptionService::getSubscriptionById) // ID로 구독 정보 조회
                .collect(Collectors.toList()); // 리스트로 변환

        // 조회된 구독 서비스 리스트를 반환
        return ResponseEntity.ok(subscriptions);
    }


    @PutMapping("/unselect/{id}")
    public ResponseEntity<String> unselectSubscription(@PathVariable Long id) {
        boolean isUnselected = subscriptionService.unselectSubscriptionById(id);
        if (isUnselected) {
            return ResponseEntity.ok("Subscription unselected successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not found.");
        }
    }
    /**
     * 모든 구독 서비스 목록을 조회하는 API
     * @return 구독 서비스 리스트
     */
    @GetMapping
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }



}