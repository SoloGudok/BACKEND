package com.example.backend.domain.subscription.service;

import com.example.backend.domain.subscription.dto.CombinationSubscriptionResponseDto;
import com.example.backend.domain.subscription.dto.SubscriptionDTO;
import com.example.backend.domain.subscription.dto.SubscriptionRes;
import com.example.backend.domain.subscription.dto.SubscriptionResponseDto;
import com.example.backend.domain.subscription.entity.Membership;
import com.example.backend.domain.subscription.entity.MembershipDetail;
import com.example.backend.domain.subscription.entity.Subscription;
import com.example.backend.domain.subscription.repository.MembershipDetailRepository;
import com.example.backend.domain.subscription.repository.MembershipRepository; // ✅ 추가
import com.example.backend.domain.subscription.repository.SubscriptionRepository;
import com.example.backend.domain.user.entity.User;
import com.example.backend.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubscriptionService {
    private final MembershipDetailRepository membershipDetailRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final MembershipRepository membershipRepository; // ✅ 추가
    private final UserRepository userRepository;

    @Autowired
    public SubscriptionService(MembershipDetailRepository membershipDetailRepository,
                               SubscriptionRepository subscriptionRepository,
                               MembershipRepository membershipRepository, // ✅ 추가
                               UserRepository userRepository) {
        this.membershipDetailRepository = membershipDetailRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.membershipRepository = membershipRepository; // ✅ 추가
        this.userRepository = userRepository;
    }

    // ✅ 카테고리 ID로 구독 리스트 조회
    public List<SubscriptionRes> getSubscriptionsByCategoryId(Long categoryId) {
        List<Subscription> subscriptions;

        // categoryId가 없거나 0이면 전체 구독 리스트 반환
        if (categoryId == null || categoryId == 0) {
            subscriptions = subscriptionRepository.findAll();
        } else {
            subscriptions = subscriptionRepository.findByCategoryId(categoryId);
        }

        return subscriptions.stream()
                .map(subscription -> SubscriptionRes.builder()
                        .id(subscription.getId())
                        .name(subscription.getName())
                        .price(subscription.getPrice())
                        .content(subscription.getContent())
                        .homepage(subscription.getHomepage())
                        .imageUrl(subscription.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

    // ✅ 개별 구독 조회
    public List<SubscriptionResponseDto> getIndividualSubscriptions(Long userId) {
        return membershipDetailRepository.findActiveMembershipDetailsForUser(userId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public List<CombinationSubscriptionResponseDto> getCombinationSubscriptions(Long userId) {
        Map<Long, List<MembershipDetail>> groupedDetails = membershipDetailRepository
                .findActiveCombinationMembershipDetailsForUser(userId)
                .stream()
                .collect(Collectors.groupingBy(detail -> detail.getMembership().getId()));

        return groupedDetails.entrySet().stream()
                .map(entry -> CombinationSubscriptionResponseDto.builder()
                        .membershipId(entry.getKey())
                        .subscriptions(entry.getValue().stream()
                                .map(this::toDto)
                                .collect(Collectors.toList()))
                        .terminationDate(entry.getValue().get(0).getCreatedAt().plusMonths(1).toLocalDate()) // ✅ 종료 날짜 설정
                        .build())
                .collect(Collectors.toList());
    }

    // ✅ MembershipDetail을 DTO로 변환
    private SubscriptionResponseDto toDto(MembershipDetail detail) {
        return SubscriptionResponseDto.builder()
                .id(detail.getSubscription().getId())
                .name(detail.getSubscription().getName())
                .price(detail.getSubscription().getPrice())
                .content(detail.getSubscription().getContent())
                .homepage(detail.getSubscription().getHomepage())
                .imageUrl(detail.getSubscription().getImageUrl())
                .terminationDate(detail.getCreatedAt().plusMonths(1).toLocalDate()) // 해지일 설정
                .build();
    }

    // ✅ 모든 구독 서비스 목록 조회
    public List<SubscriptionDTO> getAllSubscriptions() {
        long startTime = System.currentTimeMillis();

        List<SubscriptionDTO> result = subscriptionRepository.findAllWithImages().stream()
                .map(s -> new SubscriptionDTO(s, s.getImageUrl()))
                .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        log.info("getAllSubscriptions 실행 시간: {}ms", (endTime - startTime));

        return result;
    }


    // ✅ 구독 선택 해제
    public boolean unselectSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if (subscription != null) {
            subscription.setSelected(false); // 선택 상태를 false로 변경
            subscriptionRepository.save(subscription);
            return true;
        }
        return false;
    }

    // ✅ 카테고리별 구독 서비스 조회
    public List<SubscriptionDTO> getSubscriptionsByCategory(Long categoryId) {
        List<Subscription> subscriptions = subscriptionRepository.findByCategoryId(categoryId);
        return subscriptions.stream()
                .map(subscription -> new SubscriptionDTO(subscription, subscription.getImageUrl())) // ✅ getImageUrl() 사용
                .collect(Collectors.toList());
    }

    public SubscriptionDTO getSubscriptionById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .map(subscription -> new SubscriptionDTO(subscription, subscription.getImageUrl())) // 🔴 (수정됨)
                .orElse(null);
    }


}
