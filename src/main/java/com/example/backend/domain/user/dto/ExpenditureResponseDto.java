package com.example.backend.domain.user.dto;

import com.example.backend.domain.user.entity.Expenditure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
public class ExpenditureResponseDto {

    private Double totalExpense;
    private Double totalIncome;
    private List<ExpenditureDetailsDto> expenditures;  // 리스트로 변경
    private boolean hasNext;
    private Long nextCursor;  // 다음 페이지의 커서 ID

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

    // **Page 대신 List를 사용하여 변환하는 메서드**
    public static ExpenditureResponseDto fromList(
            List<Expenditure> expenditures, Double totalExpense, Double totalIncome, boolean hasNext, Long nextCursor) {

        List<ExpenditureDetailsDto> expenditureDtos = expenditures.stream()
                .map(ExpenditureDetailsDto::fromEntity)
                .collect(Collectors.toList());

        return new ExpenditureResponseDto(totalExpense, totalIncome, expenditureDtos, hasNext, nextCursor);
    }
}