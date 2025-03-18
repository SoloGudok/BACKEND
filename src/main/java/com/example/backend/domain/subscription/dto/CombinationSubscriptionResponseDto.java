package com.example.backend.domain.subscription.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CombinationSubscriptionResponseDto {
    private Long membershipId;
    private List<SubscriptionResponseDto> subscriptions;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate terminationDate;
}