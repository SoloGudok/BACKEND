package com.example.backend.domain.card.dto;

import com.example.backend.domain.card.entity.Card;
import com.example.backend.domain.subscription.dto.CategoryDTO;
import com.example.backend.domain.subscription.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Data
@NoArgsConstructor
public class CardDTO {

    private Long id;                   // 카드 ID
    private String cardName;           // 카드 이름
    private String shortDescription;   // 카드 간략 설명
    private String description;        // 카드 설명
    private List<CardImgDTO> cardImgs; // 카드 이미지 리스트
    private CategoryDTO category;
    private LocalDateTime createdAt;     // `sub.deleted_at` `cd.deleted_at`
    // 카드 정보를 포함하는 생성자
//    public CardDTO(Long id, String cardName, String shortDescription, String description, List<CardImgDTO> cardImgs, String name) {
//        this.id = id;
//        this.cardName = cardName;
//        this.shortDescription = shortDescription;
//        this.description = description;
//        this.cardImgs = cardImgs;
//
//
//    }

    // Card 엔티티를 받아서 DTO로 변환하는 생성자 추가
    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardName = card.getCardName();
        this.shortDescription = card.getShortDescription();
        this.description = card.getDescription();
        this.createdAt = card.getCreatedAt();
        this.cardImgs = card.getCardImgs().stream()
                .map(img -> new CardImgDTO(img.getId(), img.getCardId(), img.getCardImgUrl()))
                .collect(Collectors.toList());

        // 카테고리 정보 포함
        if (card.getCategory() != null) {
            this.category = new CategoryDTO(card.getCategory());
        }


    }
}