package com.example.backend.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MonthlyExpenditureRequestDto {
    private Long userId;
    private int year;
    private int month;
}