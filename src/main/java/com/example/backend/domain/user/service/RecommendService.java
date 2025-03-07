package com.example.backend.domain.user.service;

import com.example.backend.domain.user.dto.RecommendDTO;

import java.util.List;

public interface RecommendService {

    /*
    List<Object[]>를 List<DTO>로 바꾸는 작업 - 완성
    (made by 민규)
    */
    List<RecommendDTO> getRecommendations();
    RecommendDTO convertToDTO(Object[] row);
}
