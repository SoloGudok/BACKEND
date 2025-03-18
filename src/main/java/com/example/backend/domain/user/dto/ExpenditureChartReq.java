package com.example.backend.domain.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExpenditureChartReq {
    private Long categoryId;
    private int year;
    private int month;
    private Long userId;
}
