package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.dto.*;

import com.example.backend.domain.user.service.ExpenditureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/expenditure")
@Tag(name = "Expenditure", description = "소비내역 관련 api입니다.")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000","http://localhost"})
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    /**
     * 소비내역 조회 API (DTO 기반 요청)
     *
     * @param requestDto 사용자 요청 데이터 (카테고리ID, 커서, 페이지 크기, 월별 필터링 정보 포함)
     * @return ExpenditureResponseDto (총 지출금액, 총 수입금액, 소비내역 리스트, 다음 커서 값)
     */
    @PostMapping("/list")
    public ResponseEntity<ExpenditureResponseDto> getExpenditureList(
            @RequestBody ExpenditureRequestDto requestDto) {

        Pageable pageable = requestDto.toPageable();
        Long cursorId = requestDto.getCursorId(); // 요청에서 cursorId를 받음
        ExpenditureResponseDto response = expenditureService.getExpenditureList(
                requestDto.getCategoryId(),
                cursorId,
                pageable,
                requestDto.getStartDate(),
                requestDto.getEndDate()
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 소비내역 chart 조회 API (DTO 기반 요청)
     *
     * @param chartDto 사용자 요청 데이터 (카테고리ID, 년도, 월)
     * @return ExpenditureChartRes (구독 소비, 구독 외 소비 총 금액)
     */
    @PostMapping("/chart")
    public ResponseEntity<ExpenditureChartRes> getExpenditureChart(@RequestBody ExpenditureChartReq chartDto) {
        ExpenditureChartRes response = expenditureService.getExpenditureChart(chartDto);
        return ResponseEntity.ok(response);
    }

    /**
     * 월별 총 소비, 구독 소비 chart API (DTO 기반 요청)
     *
     * @RequestBody MonthlyExpenditureSummaryDto 사용자 요청 데이터 (유저ID, 년도, 월)
     * @return MonthlyExpenditureSummaryDto (구독 소비, 구독 외 소비 총 금액)
     */
    @PostMapping("/summary")
    public ResponseEntity<MonthlyExpenditureSummaryDto> getMonthlySummary(
            @RequestBody MonthlyExpenditureRequestDto request
    ) {
        MonthlyExpenditureSummaryDto result = expenditureService.getExpenditureByMonth(
                request.getUserId(), request.getYear(), request.getMonth());
        return ResponseEntity.ok(result);
    }

}
