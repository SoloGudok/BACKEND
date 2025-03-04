package com.example.backend.domain.subscription.entity;

import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;


@Getter
@Entity
@SQLDelete(sql = "UPDATE subscription SET deleted_at = NOW() where id = ?")
@NoArgsConstructor
@Table(name = "Subscription")
public class Subscription extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint")
    private Long id;


    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "INT")
    private int price;

    @Column(name = "content", nullable = true, columnDefinition = "TEXT")
    private String content;

    @Column(name = "homepage", nullable = true, columnDefinition = "VARCHAR(255)")
    private String homepage;


}
