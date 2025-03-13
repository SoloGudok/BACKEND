package com.example.backend.domain.subscription.repository;

import com.example.backend.domain.subscription.dto.SubscriptionDTO;
import com.example.backend.domain.subscription.dto.UnsubscriptionDTO;
import com.example.backend.domain.subscription.entity.Subscription;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnsubscriptionRepository extends JpaRepository<Subscription, Long> {
    /*
    해지하려고 하는 서비스 이미지 추출 (민규)
    상태 : 완성
    로직
    1. subscription 테이블에 id를 받아옴
    2. 해당 id와 동일한 id에 img 데이터 받아옴
     */
    @Query(value = "SELECT s.id AS id, s.name AS name, si.subscription_img_url AS subImgUrl " +
            "FROM subscription s " +
            "LEFT JOIN subscription_img si ON s.id = si.subscription_id " +
            "WHERE s.id = :id",
            nativeQuery = true)
    UnsubscriptionDTO getSubImg(@Param("id") Long id);

    /*
    단일 해지 (민규)
    상태 : 완성
    로직
    1. <해지 신청> 버튼 클릭
    2. email과 password 입력받음
    3. subscription 테이블에 id를 받아옴
    4. MembershipDetail의 service_id = :id 인 것의 subscription_id를 보고
    5. Membership 테이블의 subscription_id 행과 MembershipDetail 테이블의 detail_id 행의 deleted_at이 Null인 것을 지금의 시간으로 삽입
    6. Unsubscription 테이블 작성 (description_id는 Autoincrement 따르고, created_at은 지금 시간, account_email은 입력받은 email로 입력, account_password는 입력받은 password에 보안함수 적용 후 삽입, approval은 1, service_id는 3번에서 받아온 id 입력, user_id는 1로 통일 하여 행 삽입)
    7. 4번 ~ 6번을 Transaction으로 처리
    */
    // 1. membership 테이블 변경
    @Modifying
    @Transactional
    @Query("UPDATE Membership m SET m.deletedAt = CURRENT_TIMESTAMP " +
            "WHERE m = (SELECT membership FROM MembershipDetail md WHERE md.subscription.id = :subscriptionId) " +
            "AND m.deletedAt IS NULL")
    int markMembershipAsDeleted(@Param("subscriptionId") Long subscriptionId);

    // 2. membership_detail 테이블 변경
    @Modifying
    @Transactional
    @Query("UPDATE MembershipDetail md SET md.deletedAt = CURRENT_TIMESTAMP WHERE md.subscription.id = :subscriptionId AND md.deletedAt IS NULL")
    int markMembershipDetailAsDeleted(@Param("subscriptionId") Long subscriptionId);

    // 3. Unsubscription 데이터 삽입
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO unsubscription (account_email, account_password, approval, service_id, user_id, created_at, modified_at) " +
            "VALUES (:email, :password, 1, :subscriptionId, 1, NOW(), NOW())", nativeQuery = true)
    void insertUnsubscription(@Param("email") String email, @Param("password") String password, @Param("subscriptionId") Long subscriptionId);

    // 위 세 개를 Transaction 작업 처리로 해야함
    @Transactional
    default void processUnsub1(String email, String password, Long subscriptionId){
        // 1. Membership & MembershipDetail의 deleted_at 업데이트
        markMembershipAsDeleted(subscriptionId);
        markMembershipDetailAsDeleted(subscriptionId);

        // 2. Unsubscription 테이블에 데이터 삽입 (비밀번호 해싱 후 저장)
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        insertUnsubscription(email, hashedPassword, subscriptionId);
    }


    /*
    조합 해지 (조합 구독시) (민규)
    상태 : 미완성
    로직
    1. 해지 창에서 버튼 클릭
    2.
     */
    // Membership 테이블의 deleted_at 업데이트 (여러 개의 subscriptionId 처리)
    @Modifying
    @Transactional
    @Query("UPDATE Membership m SET m.deletedAt = CURRENT_TIMESTAMP " +
            "WHERE m IN (SELECT membership FROM MembershipDetail md WHERE md.subscription.id IN :subscriptionIds) " +
            "AND m.deletedAt IS NULL")
    int deleteMembership(@Param("subscriptionIds") List<Long> subscriptionIds);

    // MembershipDetail 테이블의 deleted_at 업데이트 (여러 개의 subscriptionId 처리)
    @Modifying
    @Transactional
    @Query("UPDATE MembershipDetail md SET md.deletedAt = CURRENT_TIMESTAMP " +
            "WHERE md.subscription.id IN :subscriptionIds AND md.deletedAt IS NULL")
    int deleteMembershipDetail(@Param("subscriptionIds") List<Long> subscriptionIds);

    // Unsubscription 테이블에 여러 개의 subscriptionId 삽입 (Native Query 사용)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO unsubscription (approval, service_id, user_id, created_at, modified_at) " +
            "SELECT 1, s.id, 1, NOW(), NOW() FROM subscription s WHERE s.id IN (:subscriptionIds)", nativeQuery = true)
    void insertUnsubscriptions(@Param("subscriptionIds") List<Long> subscriptionIds);

    // 여러 개 해지 트랜잭션
    @Transactional
    default void processUnsub2(List<Long> subscriptionIds) {
        // 1. Membership & MembershipDetail 테이블 업데이트
        deleteMembership(subscriptionIds);
        deleteMembershipDetail(subscriptionIds);

        // 2. Unsubscription 테이블에 데이터 삽입
        insertUnsubscriptions(subscriptionIds);
    }
}
