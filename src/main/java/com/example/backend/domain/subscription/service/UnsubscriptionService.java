package com.example.backend.domain.subscription.service;

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
}
