package com.example.backend.domain.user.service;
import com.example.backend.domain.user.dto.ChartDTO;
import com.example.backend.domain.user.dto.SubscribingDTO;

import java.util.*;
public interface DashboardService {

    // 대시보드 1 - 구독 중인 서비스 이미지 나열 (민규) - 완성
    List<SubscribingDTO> getSubscribing();
    SubscribingDTO convertSubToDTO(String str);

    // 대시보드 2.1 - 소비 차트 (민규) - 완성
    List<ChartDTO> getExpenditure();
    ChartDTO convertExpenditureToDTO(Object[] row);

    // 대시보드 2.2 - 구독 중 top3 (금액 기준) (민규)
    List<ChartDTO> getSubTop3();
    ChartDTO convertSubTop3ToDTO(Object[] row);
}
