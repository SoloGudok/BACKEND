package com.example.backend.domain.card.controller;

import com.example.backend.domain.card.dto.CardDTO;
import com.example.backend.domain.card.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // 프론트엔드 URL 허용
@Controller
@RequestMapping("/api/v1/card")
@Tag(name = "Card", description = "카드 관련 UI 페이지")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/with-images-and-category/json")
    @Operation(summary = "모든 카드와 카테고리 조회 (JSON)", description = "모든 카드, 이미지, 카테고리 정보를 JSON으로 반환합니다.")
    public List<CardDTO> getCardsWithImagesAndCategoryJson() {
        return cardService.getAllCardsWithImagesAndCategory();
    }


    // 카테고리 ID를 기준으로 필터링된 카드 리스트 반환 (categoryId가 없으면 모든 카드 반환)
    @GetMapping("/filter")
    @Operation(summary = "카드 필터링", description = "카테고리 ID를 기준으로 필터링된 카드를 반환")
    public ResponseEntity<List<CardDTO>> getFilteredCards(@RequestParam(value = "categoryId", required = false) List<Long> categoryIds) {
        System.out.println("받은 categoryIds: " + categoryIds); // 리스트 로그 확인
        try {
            List<CardDTO> filteredCards = cardService.getFilteredCards(categoryIds);
            return ResponseEntity.ok(filteredCards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cardadvertisements")
    @Operation(summary = "카드 광고 이미지 조회", description = "카드 관련 광고 이미지를 반환합니다.")
    public ResponseEntity<List<String>> getCardAdvertisements() {
        List<String> advertisementImages = List.of(
                "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/cardadvertisement1.png",
                "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/cardadvertisement2.png",
                "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/cardadvertisement3.png",
                "https://sologudok-uploaded-files.s3.ap-northeast-2.amazonaws.com/cardadvertisement4.png"
        );

        return ResponseEntity.ok(advertisementImages);
    }


}

