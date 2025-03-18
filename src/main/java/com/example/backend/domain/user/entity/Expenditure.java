package com.example.backend.domain.user.entity;

import com.example.backend.domain.subscription.entity.Category;
import com.example.backend.domain.subscription.entity.Subscription;
import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;


@Getter
@Entity
@SQLDelete(sql = "UPDATE expenditure SET deleted_at = NOW() where id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Expenditure")
public class Expenditure extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint")
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String content;

    @Column(nullable = false)
    private int amount;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private User user;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = true)
    private Subscription subscription;


    public LocalDate getDate() {
        return this.getCreatedAt().toLocalDate();
    }

    public Expenditure(User user, Category category, Subscription subscription, Long amount, String content) {
        this.user = user;
        this.category = category;  // ✅ Category 객체를 직접 넣어야 함!
        this.subscription = subscription;
        this.amount = Math.toIntExact(amount);
        this.content = content;
    }

}
