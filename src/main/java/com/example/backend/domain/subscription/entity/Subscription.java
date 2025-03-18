package com.example.backend.domain.subscription.entity;

import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // 이미지와 연결 (1:N 관계 - 여러 개 가능)
    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubscriptionImg> images = new ArrayList<>();

    // Subscription 엔티티에서 이미지 URL을 가져오는 메서드
    public String getImageUrl() {
        return !images.isEmpty() ? images.get(0).getSubscriptionImgUrl() : null;
    }
    @Column(name = "selected", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean selected = false;
    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    private String subscriptionName; // ✅ 필드가 있는지 확인!

    public String getSubscriptionName() { // ✅ getter 메서드 확인
        return subscriptionName;
    }
    public Subscription(String subscriptionName, Long price) {
        this.subscriptionName = subscriptionName;
        this.price = Math.toIntExact(price);
    }


    // 🔴(수정됨) 생성자 수정 (subscriptionName → name 사용)
    public Subscription(String name, int price) { // ✅ name 필드로 변경
        this.name = name;
        this.price = price;
    }



}
