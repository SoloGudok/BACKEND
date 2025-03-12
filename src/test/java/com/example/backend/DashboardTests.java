package com.example.backend;

import com.example.backend.domain.user.repository.DashboardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class DashboardTests {
    @Autowired
    private DashboardRepository repo;

    // 대시보드 3 - 구독 중인 서비스 이미지 나열 (민규)
    @Test
    public void test1() {
        List<String> result = repo.getSubscribing();
        for (String str : result) {
            System.out.println(str);
        }
    }
}
