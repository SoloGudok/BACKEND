package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT ci.cardImgUrl FROM User u " +
            "JOIN UserCard uc ON u.id = uc.user.id " +  // User와 Card의 연결 부분
            "JOIN uc.card c " +                      // UserCard에서 Card로 조인
            "JOIN c.cardImgs ci " +                  // Card에서 CardImg로 조인
            "WHERE u.id = :userId")
    List<String> findCardImgUrlsByUserId(@Param("userId") Long userId);

}

