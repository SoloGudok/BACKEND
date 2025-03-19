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
            LEFT JOIN membership m ON m.id = md.membership_id
            WHERE m.deleted_at IS NULL
            """, nativeQuery = true)
    List<String> getSubscribing();

    /*
    대시보드 2.1 - 소비 차트 (민규)
    상태 : 완성
    로직
    1. Category에서 category_id로 name 가져옴
    2. Expenditure에서 카테고리 별로 sum(amount) Top 3 가져옴
    3. Union
    4. name은 "기타"
    5. Expenditure에서 전체 sum(amount) - sum(amount) Top 3
     */
    @Query(value = """
            (SELECT c.name, SUM(e.amount) AS amount
             FROM expenditure e
             LEFT JOIN category c ON e.category_id = c.id
             GROUP BY c.name
             ORDER BY amount DESC
             LIMIT 3)
            
             UNION ALL
            
             (SELECT '기타' AS name,
                    (SELECT SUM(amount) FROM expenditure) -\s
                    (SELECT SUM(amount) FROM (
                        SELECT SUM(e.amount) AS amount
                        FROM expenditure e
                        LEFT JOIN category c ON e.category_id = c.id
                        GROUP BY c.name
                        ORDER BY amount DESC
                        LIMIT 3
                    ) AS top3));
            """, nativeQuery = true)
    List<Object[]> getChart1();

    /*
    대시보드 2.2 - 구독 중 top3 (금액 기준) (민규)
    상태 : 완성
    로직
    1. expenditure에서 subscription_id가 NOT NULL만 가져옴
    2. 1번 테이블에서 category_id 기준으로 GROUP BY
    3. TOP 3를 가져옴
    4. category 테이블과 JOIN
    5. category 테이블의 name을 검색함
     */
    @Query(value= """
            SELECT c.name AS name, SUM(e.amount) AS amount
            FROM expenditure e
            LEFT JOIN category c ON e.category_id = c.id
            WHERE e.subscription_id IS NOT NULL
            GROUP BY name
            ORDER BY amount desc
            LIMIT 3
            """, nativeQuery = true)
    List<Object[]> getChart2();
}
