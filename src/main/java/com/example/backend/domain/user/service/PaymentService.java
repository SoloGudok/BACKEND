package com.example.backend.domain.user.service;

import com.example.backend.domain.subscription.entity.Membership;
import com.example.backend.domain.subscription.entity.MembershipDetail;
import com.example.backend.domain.subscription.entity.Subscription;
import com.example.backend.domain.subscription.repository.MembershipDetailRepository;
import com.example.backend.domain.subscription.repository.MembershipRepository;
import com.example.backend.domain.subscription.repository.SubscriptionRepository;
import com.example.backend.domain.user.entity.User;
import com.example.backend.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final MembershipDetailRepository membershipDetailRepository;


    @Transactional
    public boolean processPayment(Long userId, List<Long> selectedSubscriptions) {
        // ✅ 유저 정보 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("❌ 유저를 찾을 수 없습니다."));

        // ✅ 선택한 구독 서비스 리스트 조회
        List<Subscription> subscriptions = subscriptionRepository.findAllById(selectedSubscriptions);
        if (subscriptions.size() != selectedSubscriptions.size()) {
            throw new RuntimeException("❌ 일부 구독 서비스를 찾을 수 없습니다.");
        }

        // ✅ 총 가격 계산 (🔥 Long 변환 추가)
        Long totalPrice = subscriptions.stream()
                .mapToLong(Subscription::getPrice)
                .sum();
        System.out.println("✅ [PaymentService] 총 금액: " + totalPrice);

        // ✅ 10% 할인 적용
        long discountedTotalPrice = Math.round(totalPrice * 0.9);
        System.out.println("✅ [PaymentService] 할인 적용 금액: " + discountedTotalPrice);

        // ✅ Membership 생성 및 저장
        Membership newMembership = new Membership(user, 1, discountedTotalPrice);
        membershipRepository.save(newMembership);

        // ✅ MembershipDetail 저장
        for (Long subscriptionId : selectedSubscriptions) {
            Subscription subscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(() -> new RuntimeException("❌ 구독 서비스 없음"));

            MembershipDetail membershipDetail = new MembershipDetail(newMembership, subscription, true);
            newMembership.addMembershipDetail(membershipDetail);
        }

        // ✅ 전체 저장
        membershipRepository.save(newMembership);
        System.out.println("✅ [PaymentService] 모든 MembershipDetail 저장 완료!");


        return true;

    }

}
