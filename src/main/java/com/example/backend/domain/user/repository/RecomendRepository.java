package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.entity.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecomendRepository extends JpaRepository<Expenditure, Long> {

    /*
     구독 서비스 추천 쿼리 (made by 민규)
     로직
        1. expenditure -> 소비 횟수가 많은 카테고리 3위까지 추리기

        2. expenditure -> 소비액이 많은 카테고리 3위까지 추리기

        3. 1,2 Union

        4. 3번 카테고리 테이블에서 membership_detail에 카테고리를 제외

        4-1. 4번 카테고리 테이블이 null이면 1번 카테고리 테이블로...

        5. 해당 카테고리의 구독 서비스 나열
     */
    @Query("""
select sub, sub_img from Subscription sub
    left outer join SubscriptionImg sub_img 
        on sub_img.subscription = sub
    left outer join MembershipDetail mem_det 
        on mem_det.subscription = sub
    left outer join Category c
        on sub.category = c
    left join Membership mem 
        on mem.id = :id
where c.id in (
    (
        select c.id, sum(e.amount) from Category c
        left outer join Expenditure e on e.category = c
        group by c.id
        order by sum(e.amount) desc
        limit 3
    ) union (
        select c.id, count(e) from Category c
        left outer join Expenditure e on e.category = c
        group by c.id
        order by count(e) desc
        limit 3
    ) minus (
        select 
    )
)
""")
}
