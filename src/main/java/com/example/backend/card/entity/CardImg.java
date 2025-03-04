package com.example.backend.card.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Card_img")
public class CardImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long card_img_id;
    private String card_img_name;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="card_id")
    private Card card;
}
