package com.example.backend.domain.subscription.dto;


import com.example.backend.domain.subscription.entity.SubscriptionImg;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter


public class SubscriptionImgDTO {
    private Long id;
    private String subscriptionImgUrl;

    // 기본 생성자
    public SubscriptionImgDTO() {
    }

    // Entity를 DTO로 변환하는 생성자
    public SubscriptionImgDTO(Long id, String subscriptionImgUrl) {
        this.id = id;
        this.subscriptionImgUrl = subscriptionImgUrl;
    }

    // Entity -> DTO 변환
    public static SubscriptionImgDTO fromEntity(SubscriptionImg subscriptionImg) {
        return new SubscriptionImgDTO(
                subscriptionImg.getId(),
                subscriptionImg.getSubscriptionImgUrl()
        );
    }

}
