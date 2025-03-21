package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor // ✅ Lombok을 사용하여 의존성 자동 주입

public class PaymentController {

    private final PaymentService paymentService; // ✅ PaymentService 의존성 주입

    @PostMapping("/payment")
    public ResponseEntity<Map<String, String>> processPayment(@RequestBody Map<String, Object> payload) {
        Map<String, String> response = new HashMap<>();  // 이 줄이 필요합니다

        try {
            // 🔥 userId를 요청 바디에서 가져옴
            Long userId = ((Number) payload.get("userId")).longValue(); // 숫자로 변환

            // ✅ selectedSubscriptions 변환 (List<Long>으로 변경)
            List<Integer> tempList = (List<Integer>) payload.get("selectedSubscriptions"); // Object → List<Integer>
            List<Long> selectedSubscriptions = tempList.stream()
                    .map(Integer::longValue) // 🔥 Integer → Long 변환
                    .toList();

            if (selectedSubscriptions == null || selectedSubscriptions.isEmpty()) {
                response.put("message", "❌ 오류: selectedSubscriptions가 null이거나 비어 있습니다!");
                return ResponseEntity.badRequest().body(response);
            }

            if (selectedSubscriptions.size() != 3) {
                response.put("message", "❌ 오류: 구독 서비스는 반드시 3개를 선택해야 합니다.");
                return ResponseEntity.badRequest().body(response);
            }

            // 🔥 결제 로직 호출
            boolean success = paymentService.processPayment(userId, selectedSubscriptions);

            if (success) {
                response.put("message", "✅ 결제가 완료되었습니다. (10% 할인 적용됨)");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "❌ 결제 처리 실패!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (RuntimeException e) {
            response.put("message", "❌ " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "❌ 요청 처리 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/single")
    public ResponseEntity<Map<String, String>> processSinglePayment(@RequestBody Map<String, Object> payload) {
        Map<String, String> response = new HashMap<>();

        try {
            Long userId = ((Number) payload.get("userId")).longValue();
            Long subscriptionId = ((Number) payload.get("subscriptionId")).longValue();
            Integer combination = (Integer) payload.get("combination"); // 🔥 combination 값을 받기


            boolean success = paymentService.processSinglePayment(userId, subscriptionId, combination);

            if (success) {
                response.put("message", "✅ 개별 구독 결제가 완료되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "❌ 개별 결제 처리 실패!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (RuntimeException e) {
            // 런타임 예외 (이미 구독 중인 서비스 등) 처리

            response.put("message", "❌ " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            // 기타 예외 처리
            e.printStackTrace();
            response.put("message", "❌ 요청 처리 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



}
