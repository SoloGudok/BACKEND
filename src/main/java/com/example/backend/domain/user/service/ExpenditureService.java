package com.example.backend.domain.user.service;

import com.example.backend.domain.user.dto.ExpenditureResponseDto;
import com.example.backend.domain.user.dto.ExpenditureSummaryDto;
import com.example.backend.domain.user.entity.Expenditure;
import com.example.backend.domain.user.repository.ExpenditureRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExpenditureService {

    private final ExpenditureRepository expenditureRepository;

    /**
     * 소비내역 조회 서비스 로직
     *
     * @param categoryId 카테고리 ID (선택)
     * @param pageable 페이지네이션 객체
     * @param startDate 조회 시작일
     * @param endDate 조회 종료일
     * @return ExpenditureResponseDto (총 지출금액, 총 수입금액, 소비내역 리스트, 다음 커서 값)
     */
    public ExpenditureResponseDto getExpenditureList(Long categoryId, Long cursorId, Pageable pageable, LocalDate startDate, LocalDate endDate) {
        // 소비내역 조회 (Page 객체 반환)
        Page<Expenditure> expenditures = expenditureRepository.findByMonthWithCursor(
                categoryId, cursorId, pageable, startDate, endDate
        );

        // 총 지출 및 수입금액 계산
        ExpenditureSummaryDto summary = expenditureRepository.calculateTotalAmounts(
                categoryId, startDate, endDate
        );

        // 다음 커서 설정 (Pageable에서는 자동으로 페이지네이션을 처리함)
        Long nextCursor = expenditures.hasNext() ? expenditures.getContent().get(expenditures.getSize() - 1).getId() : null;

        // Page<Expenditure>를 ExpenditureDetailsDto로 변환하여 반환
        return ExpenditureResponseDto.fromPage(
                expenditures,
                Double.valueOf(summary.getTotalExpense()), // int 값을 Double로 변환
                Double.valueOf(summary.getTotalIncome()),  // int 값을 Double로 변환
                nextCursor
        );
    }
}
