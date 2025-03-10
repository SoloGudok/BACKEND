package com.example.backend.domain.user.dto;

import com.example.backend.domain.user.entity.Expenditure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class ExpenditureResponseDto {

    private Double totalExpense;
    private Double totalIncome;
    private Page<ExpenditureDetailsDto> expenditures;  // Page 객체를 포함
    private Long nextCursor;

    @Getter
    @AllArgsConstructor
    public static class ExpenditureDetailsDto {

        private Long id;
        private String description;
        private Double amount;
        private LocalDate date;
        private Long categoryId;

        // 엔티티를 DTO로 변환하는 메서드
        public static ExpenditureDetailsDto fromEntity(Expenditure expenditure) {
            return new ExpenditureDetailsDto(
                    expenditure.getId(),
                    expenditure.getContent(), // 'content' 필드 사용
                    (double) expenditure.getAmount(), // amount가 int형이므로 Double로 변환
                    expenditure.getDate(),
                    expenditure.getCategory().getId()  // categoryId는 Category의 ID로 변경
            );
        }
    }

    // 엔티티 리스트를 Page DTO로 변환하는 메서드
    public static ExpenditureResponseDto fromPage(
            Page<Expenditure> page, Double totalExpense, Double totalIncome, Long nextCursor) {
        Page<ExpenditureDetailsDto> expenditureDtos = page.map(ExpenditureDetailsDto::fromEntity);

        return new ExpenditureResponseDto(totalExpense, totalIncome, expenditureDtos, nextCursor);
    }
}
