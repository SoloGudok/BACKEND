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
    public ResponseEntity<String> processPayment(@RequestBody Map<String, Object> payload) {
        System.out.println("✅ [PaymentController] 결제 요청 도착! 요청 데이터: " + payload);

        try {
            // 🔥 userId를 요청 바디에서 가져옴
            Long userId = ((Number) payload.get("userId")).longValue(); // 숫자로 변환
            System.out.println("✅ [PaymentController] userId: " + userId);

            // ✅ selectedSubscriptions 변환 (List<Long>으로 변경)
            List<Integer> tempList = (List<Integer>) payload.get("selectedSubscriptions"); // Object → List<Integer>
            List<Long> selectedSubscriptions = tempList.stream()
                    .map(Integer::longValue) // 🔥 Integer → Long 변환
                    .toList();

            if (selectedSubscriptions == null || selectedSubscriptions.isEmpty()) {
                return ResponseEntity.badRequest().body("❌ 오류: selectedSubscriptions가 null이거나 비어 있습니다!");
            }
            System.out.println("✅ [PaymentController] 선택한 구독 서비스 ID 목록: " + selectedSubscriptions);

            if (selectedSubscriptions.size() != 3) {
                return ResponseEntity.badRequest().body("❌ 오류: 구독 서비스는 반드시 3개를 선택해야 합니다.");
            }

            // 🔥 결제 로직 호출
            boolean success = paymentService.processPayment(userId, selectedSubscriptions);
            System.out.println("✅ [PaymentController] 결제 처리 결과: " + success);

            if (success) {
                return ResponseEntity.ok("✅ 결제가 완료되었습니다. (10% 할인 적용됨)");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ 결제 처리 실패!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ 요청 처리 중 오류 발생: " + e.getMessage());
        }
    }

    @PostMapping("/single")
    public ResponseEntity<Map<String, String>> processSinglePayment(@RequestBody Map<String, Object> payload) {
        Map<String, String> response = new HashMap<>();

        try {
            Long userId = ((Number) payload.get("userId")).longValue();
            Long subscriptionId = ((Number) payload.get("subscriptionId")).longValue();
            Integer combination = (Integer) payload.get("combination"); // 🔥 combination 값을 받기

            System.out.println("✅ [PaymentController] 받은 combination 값: " + combination); // 디버깅용 로그

            boolean success = paymentService.processSinglePayment(userId, subscriptionId, combination);

            if (success) {
                response.put("message", "✅ 개별 구독 결제가 완료되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "❌ 개별 결제 처리 실패!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.put("message", "❌ 요청 처리 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }




}
