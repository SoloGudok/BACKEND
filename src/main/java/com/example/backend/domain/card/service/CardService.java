package com.example.backend.domain.card.service;

import com.example.backend.domain.card.dto.CardDTO;
import com.example.backend.domain.card.dto.CardImgDTO;
import com.example.backend.domain.card.entity.Card;
import com.example.backend.domain.card.repository.CardImgRepository;
import com.example.backend.domain.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    // 모든 카드와 연결된 이미지 정보를 조회
    public List<Card> getAllCardsWithImages() {
        return cardRepository.findAllWithImages();  // Card와 연결된 이미지 정보를 함께 조회
    }
}
