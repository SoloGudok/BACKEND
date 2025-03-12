package com.example.backend.domain.card.repository;

import com.example.backend.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c LEFT JOIN FETCH c.cardImgs LEFT JOIN FETCH c.category")
    List<Card> findAllWithImagesAndCategory();

    // 단일 카테고리로 검색
    List<Card> findByCategoryId(Long categoryId);

    // 여러 카테고리로 검색 (향후 다중 카테고리 지원 시 사용)
    @Query("SELECT c FROM Card c LEFT JOIN FETCH c.cardImgs LEFT JOIN FETCH c.category WHERE c.category.id IN :categoryIds")
    List<Card> findByCategoryIdIn(@Param("categoryIds") List<Long> categoryIds);
}