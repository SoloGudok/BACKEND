package com.example.backend.domain.card.repository;

import com.example.backend.domain.card.entity.CardImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardImgRepository extends JpaRepository<CardImg, Long>

    {
        List<CardImg> findAll(); // 특정 카드의 이미지 목록 조회
}
