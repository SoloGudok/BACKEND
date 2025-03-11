package com.example.backend.domain.subscription.service;

import com.example.backend.domain.subscription.dto.CombinationSubscriptionResponseDto;
import com.example.backend.domain.subscription.dto.SubscriptionRes;
import com.example.backend.domain.subscription.dto.SubscriptionResponseDto;
import com.example.backend.domain.subscription.entity.MembershipDetail;
import com.example.backend.domain.subscription.repository.MembershipDetailRepository;
import com.example.backend.domain.subscription.repository.SubscriptionRepository;
import com.example.backend.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    private final MembershipDetailRepository membershipDetailRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SubscriptionService(MembershipDetailRepository membershipDetailRepository,
                               SubscriptionRepository subscriptionRepository,
                               UserRepository userRepository) { // 추가
        this.membershipDetailRepository = membershipDetailRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository; // 추가
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

    public List<SubscriptionResponseDto> getIndividualSubscriptions(Long userId) {
        return membershipDetailRepository.findByMembershipUserIdAndCombinationFalse(userId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<CombinationSubscriptionResponseDto> getCombinationSubscriptions(Long userId) {
        Map<Long, List<MembershipDetail>> groupedDetails = membershipDetailRepository
                .findByMembershipUserIdAndCombinationTrue(userId)
                .stream()
                .collect(Collectors.groupingBy(detail -> detail.getMembership().getId()));

        return groupedDetails.entrySet().stream()
                .map(entry -> CombinationSubscriptionResponseDto.builder()
                        .membershipId(entry.getKey())
                        .subscriptions(entry.getValue().stream()
                                .map(this::toDto)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    private SubscriptionResponseDto toDto(MembershipDetail detail) {
        return SubscriptionResponseDto.builder()
                .id(detail.getSubscription().getId())
                .name(detail.getSubscription().getName())
                .price(detail.getSubscription().getPrice())
                .content(detail.getSubscription().getContent())
                .homepage(detail.getSubscription().getHomepage())
                .terminationDate(detail.getCreatedAt().plusMonths(1).toLocalDate()) // 해지일 설정
                .build();
    }

}
