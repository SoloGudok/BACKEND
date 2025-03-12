package com.example.backend.domain.user.service;

import com.example.backend.domain.user.dto.ChartDTO;
import com.example.backend.domain.user.dto.SubscribingDTO;
import com.example.backend.domain.user.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{
    private final DashboardRepository dashboardRepository;


    @Override
    public List<SubscribingDTO> getSubscribing() {
        List<String> results= dashboardRepository.getSubscribing();
        return results.stream()
                .map(this::convertSubToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubscribingDTO convertSubToDTO(String str) {
        return SubscribingDTO.builder()
                .subImgUrl((String) str)
                .build();
    }

    @Override
    public List<ChartDTO> getExpenditure() {
        List<Object[]> results = dashboardRepository.getChart1();
        return results.stream()
                .map(this::convertExpenditureToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChartDTO convertExpenditureToDTO(Object[] row) {
        return ChartDTO.builder()
                .expenditureName((String) row[0])
                .expenditureAmount(((Number) row[1]).intValue())
                .build();

    }

    @Override
    public List<ChartDTO> getSubTop3() {
        List<Object[]> results = dashboardRepository.getChart1();
        return results.stream()
                .map(this::convertSubTop3ToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChartDTO convertSubTop3ToDTO(Object[] row) {
        return ChartDTO.builder()
                .subName((String) row[0])
                .subAmount(((Number) row[1]).intValue())
                .build();
    }
}
