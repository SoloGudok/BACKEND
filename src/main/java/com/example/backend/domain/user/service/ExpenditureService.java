package com.example.backend.domain.user.service;

import com.example.backend.domain.user.dto.*;
import com.example.backend.domain.user.entity.Expenditure;
import com.example.backend.domain.user.repository.ExpenditureRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;


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
        Slice<Expenditure> expenditures = expenditureRepository.findByMonthWithCursor(
                categoryId, cursorId, pageable, startDate, endDate
        );

        // 총 지출 및 수입금액 계산
        ExpenditureSummaryDto summary = expenditureRepository.calculateTotalAmounts(
                categoryId, startDate, endDate
        );

        List<Expenditure> expenditureList = expenditures.getContent();  // Slice → List 변환

        boolean hasNext = expenditures.hasNext();  // 다음 페이지가 있는지 확인
        Long nextCursor = (!expenditureList.isEmpty() && hasNext)
                ? expenditureList.get(expenditureList.size() - 1).getId()
                : null;


        // Slice<Expenditure>를 ExpenditureDetailsDto로 변환하여 반환
        return ExpenditureResponseDto.fromList(
                expenditureList,
                Double.valueOf(summary.getTotalExpense()),
                Double.valueOf(summary.getTotalIncome()),
                hasNext,
                nextCursor
        );
    }

    //소비내역 차트 조회 서비스 로직
    public ExpenditureChartRes getExpenditureChart(ExpenditureChartReq chartDto) {
        return expenditureRepository.findExpendituresByCriteria(chartDto);
    }

    //월별 총 소비, 구독소비
    public MonthlyExpenditureSummaryDto getExpenditureByMonth(Long userId, int year, int month) {
        return expenditureRepository.findExpenditureByMonth(userId, year, month);
    }


}
