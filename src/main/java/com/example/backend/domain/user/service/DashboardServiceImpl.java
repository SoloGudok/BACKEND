package com.example.backend.domain.user.service;

import com.example.backend.domain.user.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{
    private final DashboardRepository dashboardRepository;

    public DashboardServiceImpl(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }


    @Override
    public List<String> getSubscribing() {
        return dashboardRepository.getSubscribing();
    }
}
