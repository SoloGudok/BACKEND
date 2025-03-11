package com.example.backend.domain.subscription.repository;

import com.example.backend.domain.subscription.entity.Membership;
import com.example.backend.domain.subscription.entity.MembershipDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipDetailRepository extends JpaRepository<MembershipDetail, Long> {
    List<MembershipDetail> findByMembershipUserIdAndCombinationFalse(Long userId);
    List<MembershipDetail> findByMembershipUserIdAndCombinationTrue(Long userId);
}
