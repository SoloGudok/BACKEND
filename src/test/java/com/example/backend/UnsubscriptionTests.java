package com.example.backend;

import com.example.backend.domain.subscription.dto.UnsubscriptionDTO;
import com.example.backend.domain.subscription.repository.UnsubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class UnsubscriptionTests {
    @Autowired
    private UnsubscriptionRepository repo;

    // 해지하려고 하는 서비스 이미지 추출 (민규)
    @Test
    public void test1() {
        UnsubscriptionDTO result = repo.getSubImg(1L);
        System.out.println(result);
    }

    // 단일 해지 (민규)
    @Test
    public void test2() {
        String email = "test@example.com";
        String password = "securePassword";
        Long serviceId = 12L; // 테스트용 서비스 ID

        repo.processUnsub1(email, password, serviceId);

        System.out.println("✅ 해지 요청 성공!");
    }

    // 조합 해지 (민규)
    @Test
    public void test3() {
        List<Long> serviceId = Arrays.asList(2L, 11L, 13L);
        repo.processUnsub2(serviceId);
        System.out.println("✅ 해지 요청 성공!");
    }
}
