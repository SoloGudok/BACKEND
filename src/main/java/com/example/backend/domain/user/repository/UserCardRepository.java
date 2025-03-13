package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {
    // ✅ 특정 유저가 해당 카드를 소유하고 있는지 확인
    boolean existsByUserIdAndCardId(Long userId, Long cardId);
}
