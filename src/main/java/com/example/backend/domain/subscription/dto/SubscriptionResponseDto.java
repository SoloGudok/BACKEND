package com.example.backend.domain.subscription.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SubscriptionResponseDto {
    private Long id;
    private String name;
    private int price;
    private String content;
    private String homepage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate terminationDate;
}