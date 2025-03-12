package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    void contextLoads() {
        System.out.println(bucket);
    }

}