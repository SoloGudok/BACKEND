package com.example.backend.domain.card.entity;

import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "card")
public class Card extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String cardName;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String shortDescription;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;
}
