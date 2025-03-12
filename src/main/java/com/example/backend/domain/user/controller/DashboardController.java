package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.dto.RecommendDTO;
import com.example.backend.domain.user.dto.SubscribingDTO;
import com.example.backend.domain.user.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@Log4j2
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/subscribing")
    public ResponseEntity<List<SubscribingDTO>> getSubscribing() {
        List<SubscribingDTO> subscribingList  = dashboardService.getSubscribing();
        return ResponseEntity.ok(subscribingList);
    }
}
