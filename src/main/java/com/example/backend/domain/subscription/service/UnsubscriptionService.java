package com.example.backend.domain.subscription.service;

import com.example.backend.domain.subscription.dto.UniUnsubscriptionDTO;
import com.example.backend.domain.subscription.dto.UnsubscriptionDTO;
import com.example.backend.domain.subscription.repository.UnsubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnsubscriptionService {
    private final UnsubscriptionRepository unsubscriptionRepository;

    @Autowired
    public UnsubscriptionService(UnsubscriptionRepository unsubscriptionRepository) {
        this.unsubscriptionRepository = unsubscriptionRepository;
    }

    // 해지하려고 하는 서비스 이미지 추출 (민규)
    public UnsubscriptionDTO getUnsub(Long id) {
        return unsubscriptionRepository.getSubImg(id);
    }

    // 단일 해지 (민규)
    public void processUnsubscription(UniUnsubscriptionDTO requestDTO) {
        // 요청 데이터를 바탕으로 해지 처리
        try {
            unsubscriptionRepository.processUnsub1(
                    requestDTO.getEmail(),
                    requestDTO.getPassword(),
                    requestDTO.getServiceId()
            );
        } catch (Exception e) {
            throw new RuntimeException("해지 요청 중 오류 발생: " + e.getMessage());
        }
    }
}
