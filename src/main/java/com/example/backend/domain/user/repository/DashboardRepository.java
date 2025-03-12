package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.entity.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Expenditure, Long> {
    /*
    대시보드 1 - 구독 중인 서비스 이미지 나열 (민규)
    상태 : 완성
    로직
    1. membership_detail에서 subscription_id를 가져옴
    2. subscription_id를 가지고 subscription_img 테이블을 가져와서 img_url을 가져옴
    */
    @Query(value = """
            SELECT si.subscription_img_url
            FROM membership_detail md
            LEFT JOIN subscription_img si ON md.subscription_id = si.subscription_id
            """, nativeQuery = true)
    List<String> getSubscribing();

    /*
    대시보드 2.1 - 소비 차트 (민규)
    상태 :
    로직
    1. Category에서 category_id로 name 가져옴
    2. Expenditure에서 카테고리 별로 sum(amount) Top 3 가져옴
    3. Union
    4. name은 "기타"
    5. Category에서 전체 sum(amount) - sum(amount) Top 3
     */
    @Query(value = """
            SELECT c.name, sum(e.amount)
            FROM expenditure e LEFT JOIN category c ON e.category_id = c.id
            GROUP BY c.name
            ORDER BY sum(e.amount) desc
            LIMIT 3
            """, nativeQuery = true)
    List<Object[]> getChart1();

    /*
    대시보드 2.2 -
     */
}
