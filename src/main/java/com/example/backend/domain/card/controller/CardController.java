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

}
