package com.example.backend.domain.subscription.dto;

import com.example.backend.domain.subscription.entity.Subscription;
import lombok.Getter;

@Getter
public class SubscriptionDTO {
    private final Long id;
    private final String name;
    private final int price;
    private final String content;
    private final String homepage;
    private String imageUrl;




    public SubscriptionDTO(Subscription subscription, String imageUrl) {
        this.id = subscription.getId();
        this.name = subscription.getName();
        this.price = subscription.getPrice();
        this.content = subscription.getContent();
        this.homepage = subscription.getHomepage();
        this.imageUrl = imageUrl;  // 이미지 URL을 설정


    }


}
