package com.example.backend.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
public class ExpenditureRequestDto {

    private Long categoryId;
    private Long cursorId;  // 커서 아이디
    private LocalDate startDate;
    private LocalDate endDate;
    private int size;  // 페이지 크기

    public Pageable toPageable() {
        return PageRequest.of(0, size);
    }
}

