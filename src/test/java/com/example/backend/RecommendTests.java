package com.example.backend;

import com.example.backend.domain.user.repository.RecommendRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RecommendTests {
    @Autowired
    private RecommendRepository repo;

    /*
     구독 서비스 추천 쿼리 테스트 (made by 민규)
     */
    @Test
    public void test1() {
        List<Object[]> result = repo.getSubscriptionRecommendations();
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void test2() {
        List<Object[]> result = repo.getCardRecommendations();
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
