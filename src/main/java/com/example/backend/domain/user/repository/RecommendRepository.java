package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.entity.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Expenditure, Long> {

    /*
     대시보드 3 - 구독 서비스 추천 쿼리 (made by 민규)
     첫 제작 : 2025-03-05
     상태 : 완성
     로직
        1. expenditure -> 소비 횟수가 많은 카테고리 3위까지 추리기

        2. expenditure -> 소비액이 많은 카테고리 3위까지 추리기

        3. 1,2 Union

        4. 3번 카테고리 테이블에서 membership_detail에 카테고리를 제외

        5. 해당 카테고리의 구독 서비스 나열
     */
    @Query(value="""
            WITH TopCategories AS (
     (SELECT c.id AS id FROM category c
      LEFT JOIN expenditure e ON e.category_id = c.id
      GROUP BY c.id
      ORDER BY SUM(e.amount) DESC
      LIMIT 3)
 
     UNION
 
     (SELECT c.id AS id FROM category c
      LEFT JOIN expenditure e ON e.category_id = c.id
      GROUP BY c.id
      ORDER BY COUNT(e.id) DESC
      LIMIT 3)
 )
SELECT sub.price, sub.category_id, sub.created_at, sub.deleted_at,\s
                  sub.id AS subscription_id, sub.modified_at, sub.content,\s
                  sub.homepage, sub.name, sub_img.id AS subscription_img_id,\s
                  sub_img.subscription_img_url
 FROM subscription sub
     LEFT JOIN subscription_img sub_img ON sub_img.subscription_id = sub.id
     LEFT JOIN category c ON sub.category_id = c.id
 WHERE c.id IN (SELECT id FROM TopCategories)
 AND sub.id NOT IN (
     SELECT md.subscription_id FROM membership_detail md
     INNER JOIN membership m ON md.membership_id = m.id
     WHERE m.user_id = 1
 ) LIMIT 3
""", nativeQuery = true)
    List<Object[]> getSubscriptionRecommendations();


        /*
         대시보드 4 - 카드 추천 쿼리 (made by 민규)
         첫 제작 : 2025-03-07
         상태 : 완성
         로직
            1. expenditure -> 소비 횟수가 많은 카테고리 3위까지 추리기

            2. expenditure -> 소비액이 많은 카테고리 3위까지 추리기

            3. 1,2 Union

            4. 해당 카테고리의 카드 나열
         */
        @Query(value="""

            WITH TopCategories AS (
     (SELECT c.id AS id FROM category c
      LEFT JOIN expenditure e ON e.category_id = c.id
      GROUP BY c.id
      ORDER BY SUM(e.amount) DESC
      LIMIT 3)
 
     UNION
 
     (SELECT c.id AS id FROM category c
      LEFT JOIN expenditure e ON e.category_id = c.id
      GROUP BY c.id
      ORDER BY COUNT(e.id) DESC
      LIMIT 3)
 )
SELECT cd.created_at, cd.deleted_at, cd.id AS card_id,
       cd.modified_at, cd.card_name, cd.description, cd.short_description,
       cd.category_id, cd_img.id AS card_img_id, cd_img.card_img_url
 FROM card cd
     LEFT JOIN card_img cd_img ON cd_img.card_id = cd.id
     LEFT JOIN category c ON cd.category_id = c.id
 WHERE c.id IN (SELECT id FROM TopCategories) LIMIT 4
""", nativeQuery = true)
        List<Object[]> getCardRecommendations();
}
