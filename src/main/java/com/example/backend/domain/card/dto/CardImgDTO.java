package com.example.backend.domain.card.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CardImgDTO {

    private Long id;              // 카드 이미지 ID
    private Long cardId;          // 카드 ID (FK)
    // 카드 이미지 URL을 설정하는 메소드
    @Setter
    private String cardImgUrl;    // 카드 이미지 URL

    // 카드 이미지 정보를 포함하는 생성자
    public CardImgDTO(Long id, Long cardId, String cardImgUrl) {
        this.id = id;
        this.cardId = cardId;
        this.cardImgUrl = cardImgUrl;
    }

}
