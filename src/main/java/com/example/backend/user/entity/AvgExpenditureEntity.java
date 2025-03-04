package com.example.backend.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Avg_expenditure")
public class AvgExpenditureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avg_id;
    private int age;
    private int sub_avg_expenditure;
    private int non_sub_avg_expenditure;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="category_id")
    private CategoryEntity category;
}
