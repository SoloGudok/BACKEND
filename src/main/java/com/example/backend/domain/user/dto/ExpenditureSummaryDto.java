package com.example.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExpenditureSummaryDto {
    private int totalExpense;
    private int totalIncome;
}