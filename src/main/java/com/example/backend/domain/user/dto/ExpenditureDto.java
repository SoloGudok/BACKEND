package com.example.backend.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ExpenditureDto {
    private Long id;
    private int amount;
    private Long categoryId;
    private LocalDate date;

    public ExpenditureDto(Long id, int amount, Long categoryId, LocalDateTime createdAt) {
        this.id = id;
        this.amount = amount;
        this.categoryId = categoryId;
        this.date = createdAt.toLocalDate();  // ✅ LocalDateTime → LocalDate 변환
    }
}