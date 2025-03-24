package com.example.backend.domain.user.service;

import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.domain.subscription.entity.Membership;
import com.example.backend.domain.subscription.entity.MembershipDetail;
import com.example.backend.domain.subscription.entity.Subscription;
import com.example.backend.domain.subscription.repository.CategoryRepository;
import com.example.backend.domain.subscription.repository.MembershipDetailRepository;
import com.example.backend.domain.subscription.repository.MembershipRepository;
import com.example.backend.domain.subscription.repository.SubscriptionRepository;
import com.example.backend.domain.user.entity.Expenditure;
import com.example.backend.domain.user.entity.User;
import com.example.backend.domain.user.repository.ExpenditureRepository;
import com.example.backend.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.backend.domain.subscription.entity.QMembership.membership;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final MembershipDetailRepository membershipDetailRepository;
    private final ExpenditureRepository expenditureRepository;
    private final CategoryRepository categoryRepository;
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


        // ✅ 이미 구독 중인 서비스 체크 (각 구독 ID마다 개별 확인)
        for (Long subscriptionId : selectedSubscriptions) {
            List<String> existingSubscriptionNames =
                    membershipDetailRepository.existsByUserIdAndSubscriptionId(userId, subscriptionId);

            if (!existingSubscriptionNames.isEmpty()) {
                throw new RuntimeException("❌ 이미 구독 중인 서비스가 포함되어 있습니다: " + existingSubscriptionNames.get(0));
            }
        }

        // ✅ 총 가격 계산
        Long totalPrice = subscriptions.stream()
                .mapToLong(Subscription::getPrice)
                .sum();


        // ✅ 10% 할인 적용
        long discountedTotalPrice = Math.round(totalPrice * 0.9);


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


        // ✅ 카테고리 ID 11 조회 (🔥 필수)
        Category category = categoryRepository.findById(11L)
                .orElseThrow(() -> new RuntimeException("❌ 카테고리를 찾을 수 없습니다."));

        // ✅ 구독 서비스 이름 리스트 가져오기
        List<String> subscriptionNames = newMembership.getMembershipDetails().stream()
                .map(md -> md.getSubscription().getName())
                .collect(Collectors.toList());

        // ✅ content 문자열 만들기
        String content = "구독 조합 상품 - " + String.join(", ", subscriptionNames);

        // ✅ Expenditure 저장 (🔥 category를 객체로 설정)
        Expenditure expenditure = new Expenditure(user, category, null, discountedTotalPrice, content);
        expenditureRepository.save(expenditure);


        return true;
    }
    @Transactional
    public boolean processSinglePayment(Long userId, Long subscriptionId, int combination) {
        // ✅ 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("❌ 유저를 찾을 수 없습니다."));

        // ✅ 구독 서비스 조회
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("❌ 해당 구독 서비스를 찾을 수 없습니다."));

        // ✅ 이미 구독 중인 서비스 체크
        List<String> existingSubscriptionNames =
                membershipDetailRepository.existsByUserIdAndSubscriptionId(userId, subscriptionId);

        if (!existingSubscriptionNames.isEmpty()) {
            throw new RuntimeException("❌ 이미 구독 중인 서비스입니다: " + existingSubscriptionNames.get(0));
        }
        // ✅ 가격 가져오기
        Long price = (long) subscription.getPrice();


        // 🔥 boolean 값을 int로 변환 (에러 해결)
        boolean isCombination = (combination == 1); // ✅ 추가: 1이면 true, 0이면 false
        int combinationValue = isCombination ? 1 : 0; // ✅ boolean → int 변환

        // ✅ Membership 생성 (int 값 반영)
        Membership newMembership = new Membership(user, combinationValue, price); // 🚀 에러 해결됨!
        membershipRepository.save(newMembership);

        // ✅ MembershipDetail 저장
        MembershipDetail membershipDetail = new MembershipDetail(newMembership, subscription, isCombination);
        newMembership.addMembershipDetail(membershipDetail);
        membershipRepository.save(newMembership);



        // ✅ 카테고리 설정
        Category category = subscription.getCategory();
        if (category == null) {
            throw new RuntimeException("❌ 해당 구독 서비스의 카테고리를 찾을 수 없습니다.");
        }

        // ✅ Expenditure 저장
        Expenditure expenditure = new Expenditure(user, category, null, price, "개별 구독: " + subscription.getName());
        expenditureRepository.save(expenditure);

        return true;
    }

}
