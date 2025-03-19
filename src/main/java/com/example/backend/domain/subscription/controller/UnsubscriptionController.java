package com.example.backend.domain.subscription.controller;

import com.example.backend.domain.subscription.dto.MultiUnsubscriptionDTO;
import com.example.backend.domain.subscription.dto.UniUnsubscriptionDTO;
import com.example.backend.domain.subscription.dto.UnsubscriptionDTO;
import com.example.backend.domain.subscription.service.UnsubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/unsubscription")
@Tag(name = "Unsubscription", description = "구독 해지 관련 api입니다.")
@SessionAttributes("selectedSubscriptions")
public class UnsubscriptionController {

    private final UnsubscriptionService unsubscriptionService;

    public UnsubscriptionController(UnsubscriptionService unsubscriptionService) {
        this.unsubscriptionService = unsubscriptionService;
    }

    // 해지하려고 하는 서비스 이미지 추출 - 1개 or 3개 추출 (민규)
    @GetMapping
    public ResponseEntity<List<UnsubscriptionDTO>> getUnsub(@RequestParam List<Long> id) {
        if(id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<UnsubscriptionDTO> dto = unsubscriptionService.getUnsub(id);
        return ResponseEntity.ok(dto);
    }

    // 단일 해지 (민규)
    @PostMapping("/uni_cancel")
    public ResponseEntity<String> processUniUnsubscription(@RequestBody UniUnsubscriptionDTO requestDTO) {
        System.out.println("단일 구독 해지");
        unsubscriptionService.processUnsubscription(requestDTO);
        return ResponseEntity.ok("해지 요청이 성공적으로 처리되었습니다.");
    }

    // 조합 해지 (민규)
    @PostMapping("/multi_cancel")
    public ResponseEntity<String> processMultiUnsubscription(@RequestBody MultiUnsubscriptionDTO requestDTO) {
        System.out.println("조합 구독 해지");
        unsubscriptionService.processMultiUnsubscription(requestDTO);
        return ResponseEntity.ok("조합 구독 해지 요청이 성공적으로 처리되었습니다.");
    }
}
