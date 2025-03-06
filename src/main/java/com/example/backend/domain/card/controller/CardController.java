package com.example.backend.domain.card.controller;



import com.example.backend.domain.card.dto.CardDTO;
import com.example.backend.domain.card.service.CardService;

import com.example.backend.domain.card.entity.Card;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/api/v1/card")
//@Tag(name = "Card", description = "카드 관련 api입니다.")
@Controller
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    // 모든 카드와 그에 연결된 이미지 정보를 조회하여 Thymeleaf 템플릿에 전달
    @GetMapping("/with-images")
    public String getCardsWithImages(Model model) {
        List<Card> cards = cardService.getAllCardsWithImages();
        model.addAttribute("cards", cards);  // 카드 목록을 모델에 추가
        return "card/cards";  // "cards.html" 템플릿을 반환
    }
}