package com.example.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ExpenditureChartRes {
    private int userSubscriptionExpenditure;  // 현재 사용자의 구독 소비
    private int userNonSubscriptionExpenditure;  // 현재 사용자의 구독 외 소비
    private double avgSubscriptionExpenditure;  // 같은 나이대의 평균 구독 소비
    private double avgNonSubscriptionExpenditure;  // 같은 나이대의 평균 구독 외 소비
}
