package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.backend.domain.util.JwtUtil;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "유저 관련 API입니다.")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost"}) //CORS설정을 클래스 레벨에서
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // 유저의 cardImgUrls을 반환하는 API
    @GetMapping("/card-imgs")
    public ResponseEntity<?> getCardImgUrls(
            @RequestParam("userId") Long userId // 쿼리 파라미터로 userId 받기
    ) {
        if (userId == 1) {
            // userId가 1인 경우 카드 이미지 URL 반환
            List<String> cardImgUrls = userService.getCardImgUrlsByUserId(userId);

            if (cardImgUrls.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(cardImgUrls);
        } else {
            // userId가 1이 아닌 경우 "카드가 없습니다" 메시지 반환
            return ResponseEntity.ok("카드가 없습니다");
        }
    }
}
