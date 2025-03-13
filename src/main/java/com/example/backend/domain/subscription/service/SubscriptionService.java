package com.example.backend.domain.subscription.service;

import com.example.backend.domain.subscription.dto.CombinationSubscriptionResponseDto;
import com.example.backend.domain.subscription.dto.SubscriptionDTO;
import com.example.backend.domain.subscription.dto.SubscriptionRes;
import com.example.backend.domain.subscription.dto.SubscriptionResponseDto;
import com.example.backend.domain.subscription.entity.MembershipDetail;
import com.example.backend.domain.subscription.entity.Subscription;
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


    /**
     * 모든 구독 서비스 목록 조회
     * @return 구독 서비스 DTO 리스트
     */
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(s -> new SubscriptionDTO(s, s.getImageUrl()))  // Subscription 객체와 imageUrl을 전달
                .collect(Collectors.toList());
    }

    /**
     * ID로 특정 구독 서비스 조회
     * @param id 구독 서비스 ID
     * @return 구독 서비스 DTO
     */
    public SubscriptionDTO getSubscriptionById(Long id) {
        return subscriptionRepository.findSubscriptionWithImage(id);
    }

    // 서비스 레이어에 추가할 메서드
    public boolean unselectSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if (subscription != null) {
            subscription.setSelected(false); // 선택 상태를 false로 변경
            subscriptionRepository.save(subscription);
            return true;
        }
        return false;
    }



    public List<SubscriptionDTO> getSubscriptionsByCategory(Long categoryId) {
        List<Subscription> subscriptions = subscriptionRepository.findByCategoryId(categoryId);
        return subscriptions.stream()
                .map(subscription -> new SubscriptionDTO(subscription, subscription.getImageUrl())) // ✅ getImageUrl() 사용
                .collect(Collectors.toList());
    }

}
