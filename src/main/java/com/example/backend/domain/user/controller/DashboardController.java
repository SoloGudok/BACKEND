package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.dto.ChartDTO;
import com.example.backend.domain.user.dto.RecommendDTO;
import com.example.backend.domain.user.dto.SubscribingDTO;
import com.example.backend.domain.user.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000","http://localhost"})
public class DashboardController {
    private final DashboardService dashboardService;

    // 구독중인 서비스 이미지만 나타내는
    @GetMapping("/subscribing")
    public ResponseEntity<List<SubscribingDTO>> getSubscribing() {
        List<SubscribingDTO> subscribingList  = dashboardService.getSubscribing();
        return ResponseEntity.ok(subscribingList);
    }

    @GetMapping("/chart1")
    public ResponseEntity<List<ChartDTO>> getChart1() {
        List<ChartDTO> chart1List  = dashboardService.getExpenditure();
        return ResponseEntity.ok(chart1List);
    }

    @GetMapping("/chart2")
    public ResponseEntity<List<ChartDTO>> getChart2() {
        List<ChartDTO> chart2List  = dashboardService.getSubTop3();
        return ResponseEntity.ok(chart2List);
    }
}
