package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.dto.ExpenditureRequestDto;
import com.example.backend.domain.user.dto.ExpenditureResponseDto;
import com.example.backend.domain.user.service.ExpenditureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/expenditure")
@Tag(name = "Expenditure", description = "소비내역 관련 api입니다.")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
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


}
