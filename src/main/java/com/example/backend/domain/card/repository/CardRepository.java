package com.example.backend.domain.card.repository;

import com.example.backend.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    //card_id로 카드 이미지를 조회
    List<Card> findAll();
}
