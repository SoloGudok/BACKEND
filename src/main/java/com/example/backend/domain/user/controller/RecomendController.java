package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.dto.RecommendDTO;
import com.example.backend.domain.user.service.RecommendService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/recommend")
@Log4j2
@RequiredArgsConstructor
@Tag(name = "recommend", description = "추천 관련 api입니다.")
public class RecomendController {

    private final RecommendService recommendService;

    /*
    구독 서비스 추천 기능 - 완성
    (made by 민규)
    */
    @GetMapping("/subscription")
    public ResponseEntity<List<RecommendDTO>> getRecommendations() {
        List<RecommendDTO> recommendations = recommendService.getRecommendations();
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/card")
    public ResponseEntity<List<RecommendDTO>> getRecommendationsByCard() {
        List<RecommendDTO> recommendations = recommendService.getCardRecommendations();
        return ResponseEntity.ok(recommendations);
    }
}
