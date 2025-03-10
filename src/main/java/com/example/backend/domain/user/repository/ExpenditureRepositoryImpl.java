package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.dto.ExpenditureSummaryDto;
import com.example.backend.domain.user.entity.Expenditure;
import com.example.backend.domain.user.entity.QExpenditure;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExpenditureRepositoryImpl implements ExpenditureRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final QExpenditure expenditure = QExpenditure.expenditure;

    /**
     * 월별 소비내역 조회 (커서 기반 페이지네이션 적용)
     */
    @Override
    public Page<Expenditure> findByMonthWithCursor(Long categoryId, Long cursorId, Pageable pageable, LocalDate startDate, LocalDate endDate) {
        BooleanExpression categoryCondition = (categoryId != null) ? expenditure.category.id.eq(categoryId) : null;
        BooleanExpression dateCondition = expenditure.createdAt.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
        BooleanExpression cursorCondition = cursorId != null ? expenditure.id.gt(cursorId) : null;

        // 쿼리 실행
        List<Expenditure> expenditures = queryFactory
                .selectFrom(expenditure)
                .leftJoin(expenditure.category).fetchJoin()  // 필요한 경우 추가적인 join
                .where(categoryCondition, dateCondition, cursorCondition)
                .orderBy(expenditure.createdAt.asc(), expenditure.id.asc())  // 생성일자 순으로 정렬
                .offset(pageable.getOffset())  // 페이지네이션 offset 설정
                .limit(pageable.getPageSize())  // 페이지네이션 limit 설정 (페이지 크기만큼)
                .fetch();

        return new PageImpl<>(expenditures, pageable, expenditures.size());
    }

    /**
     * 월별 총 지출금액 및 총 수입금액 계산
     */
    @Override
    public ExpenditureSummaryDto calculateTotalAmounts(Long categoryId, LocalDate startDate, LocalDate endDate) {
        BooleanExpression categoryCondition = (categoryId != null) ? expenditure.category.id.eq(categoryId) : null;
        BooleanExpression dateCondition = expenditure.createdAt.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));

        Tuple result = queryFactory
                .select(
                        new CaseBuilder()
                                .when(expenditure.amount.lt(0))
                                .then(expenditure.amount.sum())
                                .otherwise(0),
                        new CaseBuilder()
                                .when(expenditure.amount.gt(0))
                                .then(expenditure.amount.sum())
                                .otherwise(0)
                )
                .from(expenditure)
                .where(categoryCondition, dateCondition)
                .fetchOne();

        int totalExpense = result.get(0, Integer.class);
        int totalIncome = result.get(1, Integer.class);

        return new ExpenditureSummaryDto(totalExpense, totalIncome);
    }
}