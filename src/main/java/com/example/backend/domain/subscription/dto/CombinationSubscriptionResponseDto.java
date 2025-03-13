package com.example.backend.domain.subscription.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CombinationSubscriptionResponseDto {
    private Long membershipId;
    private List<SubscriptionResponseDto> subscriptions;
}