package com.example.backend.domain.user.entity;

import com.example.backend.domain.card.entity.Card;
import jakarta.persistence.*;

@Entity
public class UserCard {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;  // Card와의 관계 설정
}

