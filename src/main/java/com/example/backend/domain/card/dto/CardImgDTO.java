package com.example.backend.domain.card.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardImgDTO {

    private Long id;           // 카드 이미지 ID (DTO에서 필수적이지 않을 수도 있음)
    private Long cardId;       // 연결된 카드 ID
    private String cardImgUrl; // 카드 이미지 URL

}
