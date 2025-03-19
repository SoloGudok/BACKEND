package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.entity.User;
import com.example.backend.domain.user.repository.UserRepository;
import com.example.backend.domain.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final Map<String, String> refreshTokens = new HashMap<>(); // ✅ Refresh Token 저장소 (실제 서비스에서는 DB 사용)

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("이메일 또는 비밀번호가 잘못되었습니다.");
        }

        String accessToken = jwtUtil.generateAccessToken(email);
        String refreshToken = jwtUtil.generateRefreshToken(email);

        refreshTokens.put(email, refreshToken); // ✅ 로그인할 때 refreshToken 저장
        System.out.println("✅ 로그인 성공 - Refresh Token 저장: " + refreshToken);

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", accessToken);
        response.put("refreshToken", refreshToken);

        return ResponseEntity.ok(response);
    }

    // ✅ Refresh Token을 이용하여 새로운 Access Token 발급
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String email = jwtUtil.validateToken(refreshToken, true);

        System.out.println("Refresh Token 요청: 이메일 = " + email);
        System.out.println("요청된 Refresh Token: " + refreshToken);
        System.out.println("저장된 Refresh Token: " + refreshTokens.get(email));

        if (email == null || !refreshToken.equals(refreshTokens.get(email))) {
            System.out.println("Refresh Token 검증 실패: 저장된 값과 다름!");
            return ResponseEntity.status(403).body("Refresh Token이 유효하지 않습니다.");
        }

        String newAccessToken = jwtUtil.generateAccessToken(email);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
}