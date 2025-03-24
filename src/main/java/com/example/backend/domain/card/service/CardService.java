package com.example.backend.domain.card.service;

import com.example.backend.domain.card.entity.Card;
import com.example.backend.domain.card.dto.CardDTO;
import com.example.backend.domain.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    // 모든 카드와 연결된 이미지 정보를 조회
    public List<CardDTO> getAllCardsWithImagesAndCategory() {
        List<Card> cards = cardRepository.findAllWithImagesAndCategory();
        return cards.stream().map(CardDTO::new).toList();
    }

    public List<CardDTO> getFilteredCards(List<Long> categoryIds) {
        List<Card> cards;

        if (categoryIds != null && !categoryIds.isEmpty()) {
            // 카테고리 ID가 있을 경우 해당 카테고리 ID에 맞는 카드만 필터링
            // 여기서 첫 번째 카테고리만 사용 (여러 카테고리 지원을 위해서는 repository 수정 필요)
            Long categoryId = categoryIds.get(0);
            cards = cardRepository.findByCategoryId(categoryId);
        } else {
            // 카테고리 ID가 없으면 모든 카드 반환
            cards = cardRepository.findAllWithImages();
        }

        return cards.stream()
                .map(CardDTO::new)  // DTO로 변환
                .collect(Collectors.toList());
    }
}