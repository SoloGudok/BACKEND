package com.example.backend.domain.card.controller;

import com.example.backend.domain.card.dto.CardDTO;
import com.example.backend.domain.card.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/card")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Card", description = "카드 관련 api입니다.")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // 카드 이름에 대한 세부 데이터를 처리하는 POST 요청
    @PostMapping("getCardDetails")
    public CardDTO getCardDetails(@RequestBody CardDTO cardDTO) {
    // Service에서 카드를 조회하여 데이터를 반환
        System.out.println(cardDTO + "설마 이거때문에?" + cardDTO.getCardName());
    return cardService.getCardDetails(cardDTO.getCardName());
    }

//    String _cardName = "신한카드 We Healing";
    String _cardImage = "https://www.shinhancard.com/pconts/images/contents/card/plate/cdCreditPOCDMG.gif";
//    String _cardShortDescription = "카드 간략 설명하는중입니다. 정보 입력해주세요.";
//    String _cardDescription = "카드 상세 설명입니다. 정보 입력해주세요.";

    @GetMapping("/sendCardDetailData")
    public ResponseEntity<Map<String, Object>> getData() {
        Map<String, Object> response = new HashMap<>();
//        response.put("cardName", _cardName);
        response.put("cardImage", _cardImage);
//        response.put("cardShortDescription", _cardShortDescription);
//        response.put("cardDescription", _cardDescription);

        return ResponseEntity.ok(response);
    }


}