package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.dto.*;
import com.example.backend.domain.user.entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
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
    public Slice<Expenditure> findByMonthWithCursor(Long categoryId, Long cursorId, Pageable pageable, LocalDate startDate, LocalDate endDate) {
        BooleanExpression categoryCondition = (categoryId != null) ? expenditure.category.id.eq(categoryId) : null;
        BooleanExpression dateCondition = expenditure.createdAt.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
        BooleanExpression cursorCondition = cursorId != null ? expenditure.id.gt(cursorId) : null;
        BooleanExpression notDeletedCondition = expenditure.deletedAt.isNull();

        // 쿼리 실행
        List<Expenditure> expenditures = queryFactory
                .selectFrom(expenditure)
                .leftJoin(expenditure.category).fetchJoin()  // 필요한 경우 추가적인 join
                .where(categoryCondition, dateCondition, cursorCondition, notDeletedCondition)
                .orderBy(expenditure.createdAt.asc(), expenditure.id.asc())  // 생성일자 순으로 정렬
                .offset(pageable.getOffset())  // 페이지네이션 offset 설정
                .limit(pageable.getPageSize()+1)  // 페이지네이션 limit 설정 (페이지 크기만큼)
                .fetch();

        return new SliceImpl<>(expenditures, pageable, expenditures.size() > pageable.getPageSize());
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


    @Override
    public ExpenditureChartRes findExpendituresByCriteria(ExpenditureChartReq requestDto) {
        QExpenditure expenditure = QExpenditure.expenditure;
        QUser user = QUser.user;
        QAvgExpenditure avgExpenditure = QAvgExpenditure.avgExpenditure;

        // 삭제되지 않은 소비내역만 조회
        BooleanExpression notDeletedCondition = expenditure.deletedAt.isNull();

        // 날짜 조건
        BooleanExpression dateCondition = expenditure.createdAt.year().eq(requestDto.getYear())
                .and(expenditure.createdAt.month().eq(requestDto.getMonth()));

        // 구독 소비 조건
        BooleanExpression subscriptionCondition = expenditure.category.id.eq(11L)
                .or(expenditure.subscription.isNotNull());

        // 구독 외 소비 조건
        BooleanExpression nonSubscriptionCondition = expenditure.category.id.ne(11L)
                .and(expenditure.subscription.isNull());

        // 카테고리 조건
        BooleanExpression categoryCondition = null;
        if (requestDto.getCategoryId() != null) {
            categoryCondition = expenditure.category.id.eq(requestDto.getCategoryId());
        }

        // 1. 사용자 정보 가져오기
        User userEntity = queryFactory
                .selectFrom(user)
                .where(user.id.eq(requestDto.getUserId()))
                .fetchOne();

        // 나이 계산 및 나이대 결정
        int ageGroup = 0;
        if (userEntity != null && userEntity.getBirth() != null) {
            int age = userEntity.calculateAge();
            ageGroup = (age / 10) * 10;
        }

        // 2. 사용자의 구독 및 비구독 지출 가져오기
        Tuple userExpenditure = queryFactory
                .select(
                        new CaseBuilder()
                                .when(subscriptionCondition)
                                .then(expenditure.amount)
                                .otherwise(0).sum(),
                        new CaseBuilder()
                                .when(nonSubscriptionCondition)
                                .then(expenditure.amount)
                                .otherwise(0).sum()
                )
                .from(expenditure)
                .where(
                        dateCondition,
                        expenditure.user.id.eq(requestDto.getUserId()),
                        categoryCondition,
                        notDeletedCondition  // 삭제되지 않은 소비내역만 포함
                )
                .fetchOne();

        int userSubscriptionExpenditure = Optional.ofNullable(userExpenditure.get(0, Integer.class)).orElse(0);
        int userNonSubscriptionExpenditure = Optional.ofNullable(userExpenditure.get(1, Integer.class)).orElse(0);

        // 3. 해당 나이대의 평균 지출 가져오기 (카테고리별)
        BooleanExpression avgCategoryCondition = null;
        if (requestDto.getCategoryId() != null) {
            avgCategoryCondition = avgExpenditure.category.id.eq(requestDto.getCategoryId());
        }

        List<Tuple> avgExpendituresList = queryFactory
                .select(
                        avgExpenditure.subAvgExpenditure,
                        avgExpenditure.nonSubAvgExpenditure
                )
                .from(avgExpenditure)
                .where(avgExpenditure.age.eq(ageGroup), avgCategoryCondition)
                .fetch();

        // 결과가 없으면 0으로 설정
        double avgSubscriptionExpenditure = 0;
        double avgNonSubscriptionExpenditure = 0;

        if (!avgExpendituresList.isEmpty()) {
            Tuple avgExpenditures = avgExpendituresList.get(0);
            avgSubscriptionExpenditure = Optional.ofNullable(avgExpenditures.get(0, Integer.class)).orElse(0);
            avgNonSubscriptionExpenditure = Optional.ofNullable(avgExpenditures.get(1, Integer.class)).orElse(0);
        }

        return new ExpenditureChartRes(
                userSubscriptionExpenditure,
                userNonSubscriptionExpenditure,
                avgSubscriptionExpenditure,
                avgNonSubscriptionExpenditure
        );
    }

    //월별 총 소비, 구독소비 금액 조회
    @Override
    public MonthlyExpenditureSummaryDto findExpenditureByMonth(Long userId, int year, int month) {
        QExpenditure expenditure = QExpenditure.expenditure;

        // 구독 소비 조건
        BooleanExpression subscriptionCondition = expenditure.category.id.eq(11L)
                .or(expenditure.subscription.isNotNull());

        // 삭제되지 않은 소비내역만 조회
        BooleanExpression notDeletedCondition = expenditure.deletedAt.isNull();

        return queryFactory
                .select(Projections.constructor(
                        MonthlyExpenditureSummaryDto.class,
                        expenditure.amount.sum().as("totalAmount"), // 전체 소비 금액
                        new CaseBuilder()
                                .when(subscriptionCondition)
                                .then(expenditure.amount)
                                .otherwise(0)
                                .sum() // 여기에서 sum()을 호출
                                .as("subscriptionAmount") // 구독 소비 금액
                ))
                .from(expenditure)
                .where(expenditure.user.id.eq(userId)
                        .and(expenditure.createdAt.year().eq(year))
                        .and(expenditure.createdAt.month().eq(month))
                        .and(notDeletedCondition))
                .fetchOne();
    }

}