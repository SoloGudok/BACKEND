package com.example.backend.domain.card.dto;

import com.example.backend.domain.card.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class CardDTO {

    private Long id;                   // 카드 ID
    private String cardName;           // 카드 이름
    private String shortDescription;   // 카드 간략 설명
    private String description;        // 카드 설명
    // 카드에 이미지 추가할 때 사용할 메소드
    @Setter
    private List<CardImgDTO> cardImgs; // 카드 이미지 리스트

    // 카드 정보를 포함하는 생성자
    public CardDTO(Long id, String cardName, String shortDescription, String description, List<CardImgDTO> cardImgs) {
        this.id = id;
        this.cardName = cardName;
        this.shortDescription = shortDescription;
        this.description = description;
        this.cardImgs = cardImgs;
    }
    // Card 엔티티를 받아서 DTO로 변환하는 생성자 추가
    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardName = card.getCardName();
        this.shortDescription = card.getShortDescription();
        this.description = card.getDescription();
    }
}
