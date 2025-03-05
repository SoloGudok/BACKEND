package com.example.backend.domain.card.controller;


import com.example.backend.domain.card.entity.Card;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/card")
@Tag(name = "Card", description = "카드 관련 api입니다.")
public class CardController {

    @Operation(summary = "샘플 카드 조회", description = "테스트용 샘플 카드 데이터를 반환합니다.")
    @GetMapping("/sample")
    public Card getSampleCard() {
        Card sampleCard = new Card();
        sampleCard.setId(1L);
        sampleCard.setCardName("Sample Card");
        sampleCard.setShortDescription("This is a sample card.");
        sampleCard.setDescription("This card is used for testing Swagger integration.");
        return sampleCard;
    }
}
