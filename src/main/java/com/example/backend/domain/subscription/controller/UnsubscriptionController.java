package com.example.backend.domain.subscription.controller;

import com.example.backend.domain.subscription.dto.UnsubscriptionDTO;
import com.example.backend.domain.subscription.service.SubscriptionService;
import com.example.backend.domain.subscription.service.UnsubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/unsubscription")
@Tag(name = "Unsubscription", description = "구독 해지 관련 api입니다.")
@SessionAttributes("selectedSubscriptions")
public class UnsubscriptionController {

    private final UnsubscriptionService unsubscriptionService;

    public UnsubscriptionController(UnsubscriptionService unsubscriptionService) {
        this.unsubscriptionService = unsubscriptionService;
    }

    // 해지하려고 하는 서비스 이미지 추출 (민규)
    @GetMapping("/{id}")
    public ResponseEntity<UnsubscriptionDTO> getUnsub(@PathVariable Long id) {
        UnsubscriptionDTO dto = unsubscriptionService.getUnsub(id);
        return ResponseEntity.ok(dto);
    }
}
