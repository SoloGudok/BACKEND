package com.example.backend.domain.user.entity;

import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "avg_expenditure")
public class AvgExpenditure extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint")
    private Long id;


    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "sub_avg_expenditure", nullable = false)
    private int subAvgExpenditure;

    @Column(name = "non_sub_avg_expenditure", nullable = false)
    private int nonSubAvgExpenditure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
