package com.example.backend.domain.dashboard.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "DashBoard", description = "대쉬보드 관련 api입니다.")
public class MainDashBoardController {
    // 광고 이미지들
    String[] _advertisement_images ={
            "https://swiperjs.com/demos/images/nature-1.jpg",
            "https://swiperjs.com/demos/images/nature-2.jpg",
            "https://swiperjs.com/demos/images/nature-3.jpg",
            "https://swiperjs.com/demos/images/nature-4.jpg",
            "https://swiperjs.com/demos/images/nature-5.jpg",
            "https://swiperjs.com/demos/images/nature-6.jpg",
            "https://swiperjs.com/demos/images/nature-7.jpg"
    };

    @GetMapping("/sendDashboardData")
    public ResponseEntity<Map<String, Object>> getData() {
        Map<String, Object> response = new HashMap<>();
        response.put("advertisementimages", _advertisement_images);
        return ResponseEntity.ok(response);
    }
}
