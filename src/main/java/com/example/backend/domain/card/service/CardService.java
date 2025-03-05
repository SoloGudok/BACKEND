package com.example.backend.domain.card.service;

import com.example.backend.domain.card.dto.CardDTO;
import com.example.backend.domain.card.dto.CardImgDTO;
import com.example.backend.domain.card.entity.Card;
import com.example.backend.domain.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    // 모든 카드 + 이미지 조회
    public List<CardDTO> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream()
                .map(CardDTO::new) // Entity → DTO 변환
                .collect(Collectors.toList());
    }
}
