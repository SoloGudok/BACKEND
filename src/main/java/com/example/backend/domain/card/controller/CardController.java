package com.example.backend.domain.card.controller;



import com.example.backend.domain.card.dto.CardDTO;
import com.example.backend.domain.card.service.CardService;

import com.example.backend.domain.card.entity.Card;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@Tag(name = "Card", description = "카드 관련 api입니다.")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // 전체 카드 이미지 목록을 반환하는 엔드포인트
    @GetMapping("/images")
    public List<CardDTO> getAllCards() {
        return cardService.getAllCards();
    }

}
