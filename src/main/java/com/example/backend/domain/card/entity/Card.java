package com.example.backend.domain.card.entity;

import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@ToString(exclude = {"cardImgs","category"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "card")
public class Card extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(name="card_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String cardName;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String shortDescription;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    //card와 cardImg의 관계 설정
    @OneToMany(mappedBy = "id")
    private List<CardImg> cardImgs;

    @ManyToOne
    //@JoinColumn(name = "category_id")
    private Category category;



}