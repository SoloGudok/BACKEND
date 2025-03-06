package com.example.backend.domain.card.repository;

import com.example.backend.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    // 모든 카드와 해당 카드에 연결된 이미지들 조회
    @Query("SELECT c FROM Card c LEFT JOIN FETCH c.cardImgs")
    List<Card> findAllWithImages();
}
