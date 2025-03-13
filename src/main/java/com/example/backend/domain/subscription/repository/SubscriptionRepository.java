package com.example.backend.domain.subscription.repository;

import com.example.backend.domain.subscription.dto.SubscriptionDTO;
import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.domain.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByCategoryId(Long categoryId);
    // 구독 서비스와 관련된 이미지도 함께 가져오기 위한 쿼리
    @Query("SELECT new com.example.backend.domain.subscription.dto.SubscriptionDTO(s, i.subscriptionImgUrl) " +
            "FROM Subscription s " +
            "LEFT JOIN s.images i " +
            "WHERE s.id = :id")
    SubscriptionDTO findSubscriptionWithImage(@Param("id") Long id);

    // 모든 구독 서비스 목록을 가져오는 쿼리
    @Query("SELECT new com.example.backend.domain.subscription.dto.SubscriptionDTO(s, i.subscriptionImgUrl) " +
            "FROM Subscription s " +
            "LEFT JOIN s.images i")
    List<SubscriptionDTO> findAllSubscriptionsWithImages();


    // 카테고리 ID로 구독 서비스 목록을 가져오는 쿼리
    @Query("SELECT new com.example.backend.domain.subscription.dto.SubscriptionDTO(s, i.subscriptionImgUrl) " +
            "FROM Subscription s " +
            "LEFT JOIN s.images i " +
            "WHERE s.category.id = :categoryId")
    List<SubscriptionDTO> findSubscriptionsByCategoryId(@Param("categoryId") Long categoryId);


}
