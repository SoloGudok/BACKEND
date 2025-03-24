package com.example.backend;

import com.example.backend.domain.card.entity.Card;
import com.example.backend.domain.card.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    CardRepository cardRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    void contextLoads() {
        System.out.println(bucket);
    }

    @Test
    void test(){
        Long starttime = System.currentTimeMillis();
        List<Card> cards = cardRepository.findAllWithImagesAndCategory();
        System.out.println(cards);
        Long endtime =System.currentTimeMillis();
        System.out.println(endtime-starttime);
    }

    @Test
    void test2(){
        Long starttime = System.currentTimeMillis();
        List<Card> cards;


        cards = cardRepository.findAllWithImages();

        System.out.println(cards);
        Long endtime =System.currentTimeMillis();
        System.out.println(endtime-starttime);
    }

}