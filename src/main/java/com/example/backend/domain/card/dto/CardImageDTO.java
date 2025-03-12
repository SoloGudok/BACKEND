package com.example.backend.domain.card.dto;

import com.example.backend.domain.card.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardImageDTO {
    private long id;
    private Card card;
    private String cardImgUrl;
}
