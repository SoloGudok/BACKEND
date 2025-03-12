package com.example.backend.domain.user.service;

import com.example.backend.domain.user.dto.RecommendDTO;
import com.example.backend.domain.user.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RecommendService {

    /*
    구독 서비스 추천
    List<Object[]>를 List<DTO>로 바꾸는 작업 - 완성
    (made by 민규)
    */
    List<RecommendDTO> getRecommendations();
    RecommendDTO convertToDTO(Object[] row);

    /*
    카드 추천
    List<Object[]>를 List<DTO>로 바꾸는 작업 - 완성
    (made by 민규)
    */
    List<RecommendDTO> getCardRecommendations();
    RecommendDTO convertCardToDTO(Object[] row);
}
