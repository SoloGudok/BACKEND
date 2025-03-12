package com.example.backend.domain.card.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/card")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Card", description = "카드 관련 api입니다.")
public class CardController {

}