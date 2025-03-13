package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.dto.ExpenditureSummaryDto;
import com.example.backend.domain.user.entity.Expenditure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface ExpenditureRepositoryCustom {
    Slice<Expenditure> findByMonthWithCursor(Long categoryId, Long cursorId, Pageable pageable, LocalDate startDate, LocalDate endDate);
    ExpenditureSummaryDto calculateTotalAmounts(Long categoryId, LocalDate startDate, LocalDate endDate);
}