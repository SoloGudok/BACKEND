package com.example.backend.domain.subscription.repository;

import com.example.backend.domain.subscription.entity.Membership;
import com.example.backend.domain.subscription.entity.MembershipDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembershipDetailRepository extends JpaRepository<MembershipDetail, Long> {
    //querydsl 대신 간단한 Query를 씀
    @Query("SELECT md FROM MembershipDetail md " +
            "JOIN md.membership m " +
            "WHERE md.deletedAt IS NULL " +
            "AND m.deletedAt IS NULL " +  // Membership의 deletedAt이 null이어야만 조회됨
            "AND md.combination = false " +
            "AND m.status = 1")
    List<MembershipDetail> findActiveMembershipDetailsForUser(Long userId);

    @Query("SELECT md FROM MembershipDetail md " +
            "JOIN md.membership m " +
            "WHERE md.deletedAt IS NULL " +
            "AND m.deletedAt IS NULL " +  // Membership의 deletedAt이 null이어야만 조회됨
            "AND md.combination = true " +
            "AND m.status = 1")
    List<MembershipDetail> findActiveCombinationMembershipDetailsForUser(Long userId);
}
