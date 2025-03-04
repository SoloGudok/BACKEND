package com.example.backend.domain.card.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "card_img")
public class CardImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint")
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String cardImgUrl;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="card_id")
    private Card card;
}
