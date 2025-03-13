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

    // 대시보드 1 - 구독 중인 서비스 이미지 나열 (민규)
    @Test
    public void test1() {
        List<String> result = repo.getSubscribing();
        for (String str : result) {
            System.out.println(str);
        }
    }


    // 대시보드 2.1 - 소비 차트 (민규)
    @Test
    public void test2() {
        List<Object[]> result = repo.getChart1();
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    // 대시보드 2.2 - 구독 차트 (민규)
    @Test
    public void test3() {
        List<Object[]> results = repo.getChart2();
        for (Object[] arr : results) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
