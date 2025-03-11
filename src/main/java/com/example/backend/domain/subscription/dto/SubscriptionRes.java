package com.example.backend.domain.subscription.dto;


import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.domain.subscription.entity.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRes {

    private Long id;
    private String name;
    private int price;
    private String content;
    private String homepage;

}
