package com.example.backend.domain.subscription.repository;

import com.example.backend.domain.subscription.entity.Membership;
import com.example.backend.domain.subscription.entity.Subscription;
import com.example.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    // 특정 사용자의 Membership 리스트 조회
    List<Membership> findByUserId(Long userId);



    // ✅ 특정 사용자의 활성화된 Membership 조회
    @Query("SELECT m FROM Membership m WHERE m.user.id = :userId AND m.status = 1")
    List<Membership> findActiveMembershipsByUserId(@Param("userId") Long userId);

    // ✅ 특정 사용자의 최신 Membership 조회
    @Query("SELECT m FROM Membership m WHERE m.user.id = :userId ORDER BY m.createdAt DESC LIMIT 1")
    Optional<Membership> findLatestMembershipByUserId(@Param("userId") Long userId);


    @Query("SELECT m FROM Membership m WHERE m.user = :user AND " +
            "EXISTS (SELECT 1 FROM MembershipDetail md WHERE md.membership = m AND md.subscription.id IN :subscriptionIds)")
    Optional<Membership> findByUserAndSubscriptionIds(@Param("user") User user, @Param("subscriptionIds") List<Long> subscriptionIds);




    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END " +
            "FROM Membership m " +
            "JOIN MembershipDetail md ON md.membership = m " +
            "WHERE m.user = :user " +
            "AND md.subscription.id IN :subscriptionIds " +
            "GROUP BY m.id HAVING COUNT(md) = :size")
    boolean existsByUserAndSubscriptionIds(@Param("user") User user,
                                           @Param("subscriptionIds") List<Long> subscriptionIds,
                                           @Param("size") long size);
}
