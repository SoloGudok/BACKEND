package com.example.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyExpenditureSummaryDto {
    private int totalAmount;         // 전체 소비 금액
    private int subscriptionAmount;  // 구독 소비 금액
}